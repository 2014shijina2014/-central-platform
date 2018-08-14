package com.central.user.service;

import java.util.Map;
import java.util.Set;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.SysPermission;
import com.central.model.user.SysRole;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
public interface SysRoleService {

	void save(SysRole sysRole);

	void update(SysRole sysRole);

	void deleteRole(Long id);

	void setPermissionToRole(Long id, Set<Long> permissionIds);

	SysRole findById(Long id);

	PageResult<SysRole> findRoles(Map<String, Object> params);

	Set<SysPermission> findPermissionsByRoleId(Long roleId);

	Result saveOrUpdate(SysRole sysRole);

}
