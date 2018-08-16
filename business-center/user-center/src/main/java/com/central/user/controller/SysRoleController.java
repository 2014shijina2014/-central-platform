package com.central.user.controller;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.SysPermission;
import com.central.model.user.SysRole;
import com.central.user.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* 角色管理
 */
@RestController
@Api(tags = "角色模块api")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 后台管理添加角色
	 * @param sysRole
	 * { 
	 *    id  : xxx
	 *    code: xxx
	 *    name: xxx
	 *    createTime: xxx
	 *    updateTime: xxx
	 * }
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:role:save')")
	@ApiOperation(value = "后台管理添加角色")
	@PostMapping("/roles")
	public SysRole save(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getCode())) {
			throw new IllegalArgumentException("角色code不能为空");
		}
		if (StringUtils.isBlank(sysRole.getName())) {
			sysRole.setName(sysRole.getCode());
		}

		sysRoleService.save(sysRole);

		return sysRole;
	}

	/**
	 * 后台管理删除角色
	 * delete /role/1
	 * @param id
	 */
	@PreAuthorize("hasAuthority('back:role:delete')")
	@ApiOperation(value = "后台管理删除角色")
	@DeleteMapping("/roles/{id}")
	public Result deleteRole(@PathVariable Long id) {
		try {
			sysRoleService.deleteRole(id);
			return Result.succeed("操作成功");
		}catch (Exception e){
			e.printStackTrace();
			return Result.succeed("操作失败");
		}
	}

	/**
	 * 后台管理修改角色
	 * put /roles
	 * @param sysRole
	 * { 
	 *    id  : xxx
	 *    code: xxx
	 *    name: xxx
	 *    createTime: xxx
	 *    updateTime: xxx
	 * }
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:role:update')")
	@ApiOperation(value = "后台管理修改角色")
	@PutMapping("/roles")
	public SysRole update(@RequestBody SysRole sysRole) {
		if (StringUtils.isBlank(sysRole.getName())) {
			throw new IllegalArgumentException("角色名不能为空");
		}

		sysRoleService.update(sysRole);

		return sysRole;
	}

	/**
	 * 后台管理给角色分配权限
	 * @param id
	 * @param permissionIds
	 */
	@PreAuthorize("hasAuthority('back:role:permission:grant')")
	@ApiOperation(value = "后台管理给角色分配权限")
	@PostMapping("/roles/{id}/permissions")
	public void setPermissionToRole(@PathVariable Long id, @RequestBody Set<Long> permissionIds) {
		sysRoleService.setPermissionToRole(id, permissionIds);
	}
	/**
	 * 后台管理获取角色的权限
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('back:role:permission:grant','role:permission:byroleid')")
	@ApiOperation(value = "后台管理 获取角色的权限")
	@GetMapping("/roles/{id}/permissions")
	public Set<SysPermission> findPermissionsByRoleId(@PathVariable Long id) {
		return sysRoleService.findPermissionsByRoleId(id);
	}

	/**
	 * 后台管理根据角色ID获取角色
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "后台管理根据角色ID获取角色")
	@PreAuthorize("hasAuthority('back:role:query')")
	@GetMapping("/roles/{id}")
	public SysRole findById(@PathVariable Long id) {
		return sysRoleService.findById(id);
	}

	/**
	 * 后台管理查询角色
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:role:query')")
	@ApiOperation(value = "后台管理查询角色")
	@GetMapping("/roles")
	public PageResult<SysRole> findRoles(@RequestParam Map<String, Object> params) {
		return sysRoleService.findRoles(params);
	}

	/**
	 * 角色新增或者删除
	 * @param sysRole
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:role:saveOrUpdate')")
	@PostMapping("/saveOrUpdate")
	public Result saveOrUpdate(@RequestBody SysRole sysRole) {
		return sysRoleService.saveOrUpdate(sysRole);
	}

}
