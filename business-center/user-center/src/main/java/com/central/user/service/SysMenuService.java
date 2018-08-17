package com.central.user.service;

import java.util.List;
import java.util.Set;

import com.central.model.user.SysMenu;

public interface SysMenuService {

	void save(SysMenu menu);

	void update(SysMenu menu);

	void delete(Long id);

	void setMenuToRole(Long roleId, Set<Long> menuIds);

	List<SysMenu> findByRoles(Set<Long> roleIds);

	List<SysMenu> findAll();

	SysMenu findById(Long id);

	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<SysMenu> findOnes();


}
