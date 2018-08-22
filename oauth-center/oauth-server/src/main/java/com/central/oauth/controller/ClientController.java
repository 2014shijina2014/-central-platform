package com.central.oauth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.model.common.PageResult;
import com.central.oauth.dao.ClientDao;
import com.central.oauth.dto.ClientDto;
import com.central.oauth.model.Client;
import com.central.oauth.service.ClientService;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色相关接口
 *
 * @author owen 624191343@qq.com
 */
@Api(tags = "应用")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientDao clientDao;

    @PostMapping
    @ApiOperation(value = "保存应用")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public void saveRole(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientDto);
    }

    @GetMapping
    @ApiOperation(value = "应用列表")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public PageResult<Client> listRoles(@RequestParam Map<String, Object> params) {
        return clientService.listRoles(params) ;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取应用")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Client get(@PathVariable Long id) {
        return clientDao.getById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有应用")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<Client> roles() {
        return clientDao.findList(Maps.newHashMap());
    }

    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<Client> roles(Long userId) {
        return clientDao.listByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除应用")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
