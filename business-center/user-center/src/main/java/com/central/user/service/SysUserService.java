package com.central.user.service;

import java.util.Map;
import java.util.Set;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.LoginAppUser;
import com.central.model.user.SysRole;
import com.central.model.user.SysUser;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
public interface SysUserService {

	void addSysUser(SysUser sysUser);

	void updateSysUser(SysUser sysUser);

	LoginAppUser findByUsername(String username);

	SysUser findById(Long id);

	void setRoleToUser(Long id, Set<Long> roleIds);

	void updatePassword(Long id, String oldPassword, String newPassword);

	PageResult<SysUser> findUsers(Map<String, Object> params);

	Set<SysRole> findRolesByUserId(Long userId);


	Result updateEnabled(Map<String, Object> params);

	Result saveOrUpdate(SysUser sysUser);

}
