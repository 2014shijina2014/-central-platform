package com.ctrip.framework.apollo.internals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctrip.framework.apollo.core.utils.ClassLoaderUtil;
import com.ctrip.framework.apollo.enums.PropertyChangeType;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.tracer.Tracer;
import com.ctrip.framework.apollo.util.ExceptionUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.RateLimiter;


/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class DefaultConfig extends AbstractConfig implements RepositoryChangeListener {
  private static final Logger logger = LoggerFactory.getLogger(DefaultConfig.class);
  private final String m_namespace;
  private Properties m_resourceProperties;
  private AtomicReference<Properties> m_configProperties;
  private ConfigRepository m_configRepository;
  private RateLimiter m_warnLogRateLimiter;

  /**
   * Constructor.
   *
   * @param namespace        the namespace of this config instance
   * @param configRepository the config repository for this config instance
   */
  public DefaultConfig(String namespace, ConfigRepository configRepository) {
    m_namespace = namespace;
    m_resourceProperties = loadFromResource(m_namespace);
    m_configRepository = configRepository;
    m_configProperties = new AtomicReference<>();
    m_warnLogRateLimiter = RateLimiter.create(0.017); // 1 warning log output per minute
    initialize();
  }

  private void initialize() {
    try {
      m_configProperties.set(m_configRepository.getConfig());
    } catch (Throwable ex) {
      Tracer.logError(ex);
      logger.warn("Init Apollo Local Config failed - namespace: {}, reason: {}.",
          m_namespace, ExceptionUtil.getDetailMessage(ex));
    } finally {
      //register the change listener no matter config repository is working or not
      //so that whenever config repository is recovered, config could get changed
      m_configRepository.addChangeListener(this);
    }
  }

  @Override
  public String getProperty(String key, String defaultValue) {
    // step 1: check system properties, i.e. -Dkey=value
    String value = System.getProperty(key);

    // step 2: check local cached properties file
    if (value == null && m_configProperties.get() != null) {
      value = m_configProperties.get().getProperty(key);
    }

    /**
     * step 3: check env variable, i.e. PATH=...
     * normally system environment variables are in UPPERCASE, however there might be exceptions.
     * so the caller should provide the key in the right case
     */
    if (value == null) {
      value = System.getenv(key);
    }

    // step 4: check properties file from classpath
    if (value == null && m_resourceProperties != null) {
      value = (String) m_resourceProperties.get(key);
    }

    if (value == null && m_configProperties.get() == null && m_warnLogRateLimiter.tryAcquire()) {
      logger.warn("Could not load config for namespace {} from Apollo, please check whether the configs are released in Apollo! Return default value now!", m_namespace);
    }

    return value == null ? defaultValue : value;
  }

  @Override
  public Set<String> getPropertyNames() {
    Properties properties = m_configProperties.get();
    if (properties == null) {
      return Collections.emptySet();
    }

    return properties.stringPropertyNames();
  }

  @Override
  public synchronized void onRepositoryChange(String namespace, Properties newProperties) {
    if (newProperties.equals(m_configProperties.get())) {
      return;
    }
    Properties newConfigProperties = new Properties();
    newConfigProperties.putAll(newProperties);

    Map<String, ConfigChange> actualChanges = updateAndCalcConfigChanges(newConfigProperties);

    //check double checked result
    if (actualChanges.isEmpty()) {
      return;
    }

    this.fireConfigChange(new ConfigChangeEvent(m_namespace, actualChanges));

    Tracer.logEvent("Apollo.Client.ConfigChanges", m_namespace);
  }

  private Map<String, ConfigChange> updateAndCalcConfigChanges(Properties newConfigProperties) {
    List<ConfigChange> configChanges =
        calcPropertyChanges(m_namespace, m_configProperties.get(), newConfigProperties);

    ImmutableMap.Builder<String, ConfigChange> actualChanges =
        new ImmutableMap.Builder<>();

    /** === Double check since DefaultConfig has multiple config sources ==== **/

    //1. use getProperty to update configChanges's old value
    for (ConfigChange change : configChanges) {
      change.setOldValue(this.getProperty(change.getPropertyName(), change.getOldValue()));
    }

    //2. update m_configProperties
    m_configProperties.set(newConfigProperties);
    clearConfigCache();

    //3. use getProperty to update configChange's new value and calc the final changes
    for (ConfigChange change : configChanges) {
      change.setNewValue(this.getProperty(change.getPropertyName(), change.getNewValue()));
      switch (change.getChangeType()) {
        case ADDED:
          if (Objects.equals(change.getOldValue(), change.getNewValue())) {
            break;
          }
          if (change.getOldValue() != null) {
            change.setChangeType(PropertyChangeType.MODIFIED);
          }
          actualChanges.put(change.getPropertyName(), change);
          break;
        case MODIFIED:
          if (!Objects.equals(change.getOldValue(), change.getNewValue())) {
            actualChanges.put(change.getPropertyName(), change);
          }
          break;
        case DELETED:
          if (Objects.equals(change.getOldValue(), change.getNewValue())) {
            break;
          }
          if (change.getNewValue() != null) {
            change.setChangeType(PropertyChangeType.MODIFIED);
          }
          actualChanges.put(change.getPropertyName(), change);
          break;
        default:
          //do nothing
          break;
      }
    }
    return actualChanges.build();
  }

  private Properties loadFromResource(String namespace) {
    String name = String.format("META-INF/config/%s.properties", namespace);
    InputStream in = ClassLoaderUtil.getLoader().getResourceAsStream(name);
    Properties properties = null;

    if (in != null) {
      properties = new Properties();

      try {
        properties.load(in);
      } catch (IOException ex) {
        Tracer.logError(ex);
        logger.error("Load resource config for namespace {} failed", namespace, ex);
      } finally {
        try {
          in.close();
        } catch (IOException ex) {
          // ignore
        }
      }
    }

    return properties;
  }
}
