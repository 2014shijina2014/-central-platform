package com.central.user.service;

import java.util.Map;
import java.util.Set;

import com.central.model.common.PageResult;
import com.central.model.user.SysPermission;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
public interface SysPermissionService {

	/**
	 * 根绝角色ids获取权限集合
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<SysPermission> findByRoleIds(Set<Long> roleIds);

	void save(SysPermission sysPermission);

	void update(SysPermission sysPermission);

	void delete(Long id);

	PageResult<SysPermission> findPermissions(Map<String, Object> params);
}
