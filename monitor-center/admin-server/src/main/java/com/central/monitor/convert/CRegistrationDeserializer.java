package com.central.monitor.convert;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
@Component
public class CRegistrationDeserializer extends RegistrationDeserializer{

	 public CRegistrationDeserializer() {
	        super();
	    }

	    @Override
	    public Registration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	        JsonNode node = p.readValueAsTree();
	        Registration.Builder builder = Registration.builder();

	        if (node.has("name")) {
	            builder.name(node.get("name").asText());
	        }
	        if (node.has("url")) {
	            String url = node.get("url").asText();
	            builder.healthUrl(url.replaceFirst("/+$", "") + "/health").managementUrl(url);
	        } else {
	            if (node.has("healthUrl")) {
	                builder.healthUrl(node.get("healthUrl").asText());
	            }
	            if (node.has("managementUrl")) {
	                builder.managementUrl(node.get("managementUrl").asText() );
	            }
	            if (node.has("serviceUrl")) {
	                builder.serviceUrl(node.get("serviceUrl").asText() + "doc.html" );
	            }
	        }

	        if (node.has("metadata")) {
	            Iterator<Map.Entry<String, JsonNode>> it = node.get("metadata").fields();
	            while (it.hasNext()) {
	                Map.Entry<String, JsonNode> entry = it.next();
	                builder.metadata(entry.getKey(), entry.getValue().asText());
	            }
	        }
	        return builder.build();
	    }

}
