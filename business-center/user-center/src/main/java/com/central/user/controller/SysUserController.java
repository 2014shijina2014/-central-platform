package com.central.user.controller;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.common.utils.SysUserUtil;
import com.central.model.user.LoginAppUser;
import com.central.model.user.SysRole;
import com.central.model.user.SysUser;
import com.central.user.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 *用户
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {

    @Autowired
    private SysUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 当前登录用户 LoginAppUser
     *
     * @return
     */
    @ApiOperation(value = "根据access_token当前登录用户")
    @GetMapping("/users/current")
    public LoginAppUser getLoginAppUser() {
        return SysUserUtil.getLoginAppUser();
    }
    
    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
    }

    /**
     * 用户查询
     * http://192.168.3.2:7000/users?access_token=3b45d059-601b-4c63-85f9-9d77128ee94d&start=0&length=10
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('back:user:query')")
    @ApiOperation(value = "用户查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/users")
//  searchKey=username, searchValue=as
    public PageResult<SysUser> findUsers(@RequestParam Map<String, Object> params) {
        return appUserService.findUsers(params);
    }

    @PreAuthorize("hasAuthority('back:user:query')")
    @GetMapping("/users/{id}")
    public SysUser findUserById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    /**
     * 添加用户,根据用户名注册
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/users-anon/register")
    public SysUser register(@RequestBody SysUser sysUser) {
        appUserService.addSysUser(sysUser);

        return sysUser;
    }

    /**
     * 修改自己的个人信息
     *
     * @param sysUser
     * @return
     */
    @PutMapping("/users/me")
    public SysUser updateMe(@RequestBody SysUser sysUser) {
        SysUser user = SysUserUtil.getLoginAppUser();
        sysUser.setId(user.getId());

        appUserService.updateSysUser(sysUser);

        return sysUser;
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     */
    @PutMapping(value = "/users/password", params = {"oldPassword", "newPassword"})
    public void updatePassword(String oldPassword, String newPassword) {
        if (StringUtils.isBlank(oldPassword)) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPassword)) {
            throw new IllegalArgumentException("新密码不能为空");
        }

        SysUser user = SysUserUtil.getLoginAppUser();
        appUserService.updatePassword(user.getId(), oldPassword, newPassword);
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     * @param newPassword
     */
    @PreAuthorize("hasAuthority('back:user:password')")
    @PutMapping(value = "/users/{id}/password", params = {"newPassword"})
    public void resetPassword(@PathVariable Long id, String newPassword) {
        appUserService.updatePassword(id, null, newPassword);
    }

    /**
     * 管理后台修改用户
     *
     * @param sysUser
     */
    @PreAuthorize("hasAuthority('back:user:update')")
    @PutMapping("/users")
    public void updateSysUser(@RequestBody SysUser sysUser) {
        appUserService.updateSysUser(sysUser);
    }

    /**
     * 管理后台给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    @PreAuthorize("hasAuthority('back:user:role:set')")
    @PostMapping("/users/{id}/roles")
    public void setRoleToUser(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        appUserService.setRoleToUser(id, roleIds);
    }

    /**
     * 获取用户的角色
     *
     * @param
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:user:role:set','user:role:byuid')")
    @GetMapping("/users/{id}/roles")
    public Set<SysRole> findRolesByUserId(@PathVariable Long id) {
        return appUserService.findRolesByUserId(id);
    }


    /**
     *  修改用户状态
     * @param params
     * @return
     * @author gitgeek
     */
    @ApiOperation(value = "修改用户状态")
    @GetMapping("/users/updateEnabled")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "enabled",value = "是否启用", required = true, dataType = "Boolean")
    })
    public Result updateEnabled(@RequestParam Map<String, Object> params){
        return appUserService.updateEnabled(params);
    }

    /**
     * 管理后台，给用户重置密码
     * @param id
     * @author gitgeek
     */
    @PreAuthorize("hasAuthority('back:user:password')")
    @PostMapping(value = "/users/{id}/resetPassword")
    public Result resetPassword(@PathVariable Long id) {
        appUserService.updatePassword(id, null, "123456");
        return Result.succeed(null,"重置成功");
    }


    /**
     * 新增or更新
     * @param sysUser
     * @return
     */
    @PostMapping("/users/saveOrUpdate")
    @PreAuthorize("hasAuthority('back:user:saveOrUpdate')")
    public Result saveOrUpdate(@RequestBody SysUser sysUser) {
        return  appUserService.saveOrUpdate(sysUser);
    }


}
