package com.central.oauth.service;

import java.util.List;
import java.util.Map;

import com.central.model.common.Result;
import org.apache.ibatis.annotations.Param;

import com.central.model.common.PageResult;
import com.central.oauth.dto.ClientDto;
import com.central.oauth.model.Client;

public interface IClientService {

	
	Client getById(Long id) ;
	 
    void saveClient(ClientDto clientDto);

    Result saveOrUpdate(ClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<Client> listRoles(Map<String, Object> params);
    
    List<Client> findList(Map<String, Object> params) ;
    
    List<Client> listByUserId(Long userId) ;
    
}
