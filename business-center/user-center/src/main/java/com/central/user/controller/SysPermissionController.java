package com.central.user.controller;

import java.util.Map;

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
	@PreAuthorize("hasAuthority('back:permission:save')")
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
	@PreAuthorize("hasAuthority('back:permission:update')")
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
	@PreAuthorize("hasAuthority('back:permission:delete')")
	@ApiOperation(value = "后台管理删除权限标识")
	@DeleteMapping("/permissions/{id}")
	public void delete(@PathVariable Long id) {
		sysPermissionService.delete(id);
	}

	/**
	 * 查询所有的权限标识
	 * 参考 ?start=0&length=10
	 * @return
	 */
	
	@PreAuthorize("hasAuthority('back:permission:query')")
	@ApiOperation(value = "后台管理查询所有的权限标识")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
	@GetMapping("/permissions")
	public PageResult<SysPermission> findPermissions(@RequestParam Map<String, Object> params) {
		return sysPermissionService.findPermissions(params);
	}
}
