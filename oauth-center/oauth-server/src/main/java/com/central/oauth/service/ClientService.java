package com.central.oauth.service;

import java.util.Map;

import com.central.model.common.PageResult;
import com.central.oauth.dto.ClientDto;
import com.central.oauth.model.Client;

public interface ClientService {

    void saveClient(ClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<Client> listRoles( Map<String, Object> params);
}
