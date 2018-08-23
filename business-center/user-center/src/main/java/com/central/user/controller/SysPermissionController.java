package com.central.user.controller;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.central.model.common.Result;
import com.central.model.user.SysMenu;
import com.central.model.user.SysRole;
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
import com.central.model.user.SysPermission;
import com.central.user.service.SysPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* 权限管理
 */
@RestController
@Api(tags = "权限模块api")
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 后台管理添加权限
	 * @param sysPermission
	 * {
	 *   id : *** ,
	 *   permission : *** ,
	 *   name: *** ,
	 *   createTime: *** ,
	 *   updateTime: *** 
	 * }
	 * @return
	 */
	@PreAuthorize("hasAuthority('permission:post/permissions')")
	@ApiOperation(value = "后台管理添加权限")
	@PostMapping("/permissions")
	public SysPermission save(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getPermission())) {
			throw new IllegalArgumentException("权限标识不能为空");
		}
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.save(sysPermission);

		return sysPermission;
	}

	/**
	 * 后台管理修改权限
	 * @param sysPermission
	 * {
	 *   id : *** ,
	 *   permission : *** ,
	 *   name: *** ,
	 *   createTime: *** ,
	 *   updateTime: *** 
	 * }
	 * @return
	 */
	@PreAuthorize("hasAuthority('permission:put/permissions')")
	@ApiOperation(value = "后台管理修改权限")
	@PutMapping("/permissions")
	public SysPermission update(@RequestBody SysPermission sysPermission) {
		if (StringUtils.isBlank(sysPermission.getName())) {
			throw new IllegalArgumentException("权限名不能为空");
		}

		sysPermissionService.update(sysPermission);

		return sysPermission;
	}

	/**
	 * 删除权限标识
	 * 参考 /permissions/1
	 * @param id
	 */
	@PreAuthorize("hasAuthority('permission:delete/permissions/{id}')")
	@ApiOperation(value = "后台管理删除权限标识")
	@DeleteMapping("/permissions/{id}")
	public Result delete(@PathVariable Long id) {

		try{
			sysPermissionService.delete(id);
			return  Result.succeed("操作成功");
		}catch (Exception ex){
			ex.printStackTrace();
			return  Result.failed("操作失败");
		}

	}


	/**
	 * 查询所有的权限标识
	 * 参考 ?start=0&length=10
	 * @return
	 */
	@PreAuthorize("hasAuthority('permission:get/permissions')")
	@ApiOperation(value = "后台管理查询所有的权限标识")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
	@GetMapping("/permissions")
	public PageResult<SysPermission> findPermissions(@RequestParam Map<String, Object> params) {
		return sysPermissionService.findPermissions(params);
	}

	/**
	 * 权限新增或者更新
	 * @param sysPermission
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('permission:put/permissions','permission:post/permissions')")
	@PostMapping("/permissions/saveOrUpdate")
	public Result saveOrUpdate(@RequestBody SysPermission sysPermission) {
		try{
			if (sysPermission.getId()!=null){
				sysPermissionService.update(sysPermission);
			}else {
				sysPermissionService.save(sysPermission);
			}
			return Result.succeed("操作成功");
		}catch (Exception ex){
			return Result.failed("操作失败");
		}
	}

	@PreAuthorize("hasAuthority('permission:get/permissions/{roleId}/permissions')")
	@ApiOperation(value = "根据roleId获取对应的权限")
	@GetMapping("/permissions/{roleId}/permissions")
	public List<Map<String, Object>> findAuthByRoleId(@PathVariable Long roleId) {
		List<Map<String, Object>> authTrees = new ArrayList<>();
		Set<Long> roleIds = new HashSet<Long>() {{ add(roleId); }};
		Set<SysPermission> roleAuths = sysPermissionService.findByRoleIds(roleIds);//根据roleId获取对应的权限
		PageResult<SysPermission> allAuths = sysPermissionService.findPermissions(null);//根据roleId获取对应的权限


		Map<Long,SysPermission> roleAuthsMap = roleAuths.stream().collect(Collectors.toMap(SysPermission::getId,SysPermission->SysPermission));

		for (SysPermission sysPermission : allAuths.getData() ){
			Map<String, Object> authTree = new HashMap<>();
			authTree.put("id",sysPermission.getId());
			authTree.put("name",sysPermission.getName());
			authTree.put("open",true);
			authTree.put("checked", false);
			if (roleAuthsMap.get(sysPermission.getId())!=null){
				authTree.put("checked", true);
			}
			authTrees.add(authTree);
		}

		return authTrees;
	}
	/**
	 * 给角色分配权限
	 */
	@PreAuthorize("hasAuthority('permission:post/permissions/granted')")
	@ApiOperation(value = "角色分配权限")
	@PostMapping("/permissions/granted")
	public Result setAuthToRole(@RequestBody SysPermission sysPermission) {
		System.out.println(sysPermission);
		sysPermissionService.setAuthToRole(sysPermission.getRoleId(),sysPermission.getAuthIds());
		return Result.succeed("操作成功");
	}












}
