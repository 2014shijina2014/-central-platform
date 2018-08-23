package com.central.oauth.service;

import com.central.model.user.SysMenu;
import com.central.oauth.model.SysService;

import java.util.List;
import java.util.Set;

public interface ISysServiceService {

	/**
	 * 添加服务
	 * @param service
	 */
	void save(SysService service);

	/**
	 * 更新服务
	 * @param service
	 */
	void update(SysService service);

	/**
	 * 删除服务
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 客户端分配服务
	 * @param clientId
	 * @param menuIds
	 */
	void setMenuToClient(Long clientId, Set<Long> menuIds);

	/**
	 * 客户端服务列表
	 * @param roleIds
	 * @return
	 */
	List<SysService> findByClient(Set<Long> roleIds);

	/**
	 * 服务列表
	 * @return
	 */
	List<SysService> findAll();

	/**
	 * ID获取服务
	 * @param id
	 * @return
	 */
	SysMenu findById(Long id);

	/**
	 * 角色ID获取服务
	 * @param roleId
	 * @return
	 */
	Set<Long> findClientIdsByRoleId(Long roleId);

	/**
	 * 一级服务
	 * @return
	 */
	List<SysService> findOnes();


}
