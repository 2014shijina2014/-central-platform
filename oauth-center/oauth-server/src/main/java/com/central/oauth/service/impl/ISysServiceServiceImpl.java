package com.central.oauth.service.impl;

import com.central.model.user.SysMenu;
import com.central.oauth.dao.SysServiceDao;
import com.central.oauth.model.SysService;
import com.central.oauth.service.ISysServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author: [gitgeek]
 * @Date: [2018-08-23 15:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */

@Slf4j
@Service
public class ISysServiceServiceImpl implements ISysServiceService {


    @Autowired
    private SysServiceDao sysServiceDao;


    /**
     * 添加服务
     *
     * @param service
     */
    @Override
    public void save(SysService service) {

    }

    /**
     * 更新服务
     *
     * @param service
     */
    @Override
    public void update(SysService service) {

    }

    /**
     * 删除服务
     *
     * @param id
     */
    @Override
    public void delete(Long id) {

    }

    /**
     * 客户端分配服务
     *
     * @param clientId
     * @param menuIds
     */
    @Override
    public void setMenuToClient(Long clientId, Set<Long> menuIds) {

    }

    /**
     * 客户端服务列表
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<SysService> findByClient(Set<Long> roleIds) {
        return null;
    }

    /**
     * 服务列表
     *
     * @return
     */
    @Override
    public List<SysService> findAll() {
        return null;
    }

    /**
     * ID获取服务
     *
     * @param id
     * @return
     */
    @Override
    public SysMenu findById(Long id) {
        return null;
    }

    /**
     * 角色ID获取服务
     *
     * @param roleId
     * @return
     */
    @Override
    public Set<Long> findClientIdsByRoleId(Long roleId) {
        return null;
    }

    /**
     * 一级服务
     *
     * @return
     */
    @Override
    public List<SysService> findOnes() {
        return null;
    }
}
