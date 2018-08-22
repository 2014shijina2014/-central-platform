package com.central.oauth.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.model.user.SysPermission;
import com.central.oauth.dao.ClientDao;
import com.central.oauth.dto.ClientDto;
import com.central.oauth.model.Client;
import com.central.oauth.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "oauth_client_details";

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Transactional
    public void saveClient(ClientDto clientDto) {
        Client client = clientDto;
        List<Long> permissionIds = clientDto.getPermissionIds();
        permissionIds.remove(0L);

        if (client.getId() != null) {// 修改
            updateClient(client, permissionIds);
        } else {// 新增
            saveClient(client, permissionIds);
        }
    }

    private void saveClient(Client client, List<Long> permissionIds) {
        Client r = clientDao.getClient(client.getClientId());
        if (r != null) {
            throw new IllegalArgumentException(client.getClientId() + "已存在");
        }

        clientDao.save(client);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            clientDao.saveClientPermission(client.getId(), permissionIds);
        }
        log.debug("新增应用{}", client.getClientId());
    }

    private void updateClient(Client client, List<Long> permissionIds) {
//		Client r = clientDao.getClient(client.getClientId());
//		if (r != null && r.getId() != client.getId()) {
//			throw new IllegalArgumentException(client.getClientId() + "已存在");
//		}

        clientDao.update(client);


        clientDao.deleteClientPermission(client.getId());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            clientDao.saveClientPermission(client.getId(), permissionIds);
        }

        String clientId = clientDao.getById(client.getId()).getClientId();

        BaseClientDetails clientDetails = null;

        // 先从redis获取
        try {
            String value = (String) redisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
            clientDetails.setClientSecret(client.getClientSecret());
            redisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(clientId, JSONObject.toJSONString(clientDetails));


        } catch (Exception e) {

        }


        log.debug("修改应用{}", client.getClientId());
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientDao.deleteClientPermission(id);
        clientDao.delete(id);

        log.debug("删除应用id:{}", id);
    }

	@Override
	public PageResult<Client> listRoles(Map<String, Object> params) {
		// TODO Auto-generated method stub
		int total = clientDao.count(params);
		List<Client> list = Collections.emptyList();

		if (total > 0) {
			PageUtil.pageParamConver(params, false);
			list = clientDao.findList(params);

		}
		return PageResult.<Client>builder().data(list).code(0).count((long)total).build()  ;
	}

}
