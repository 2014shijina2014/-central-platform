package com.central.oauth.controller;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.SysMenu;
import com.central.oauth.model.SysService;
import com.central.oauth.service.ISysServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: [gitgeek]
 * @Date: [2018-08-23 16:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@RestController
@Api(tags = "服务模块api")
@RequestMapping("/services")
public class SysServiceController {

    @Autowired
    private ISysServiceService iSysServiceService;

    /**
     * 查询所有服务
     * @return
     */
    @PreAuthorize("hasAuthority('service:get/service/findAlls')")
    @ApiOperation(value = "查询所有服务")
    @GetMapping("/findAlls")
    public PageResult<SysService> findAlls() {
        List<SysService> list = iSysServiceService.findAll();

        return PageResult.<SysService>builder().data(list).code(0).count((long)list.size()).build() ;
    }

    /**
     * 获取服务以及顶级服务
     * @return
     */
    @ApiOperation(value = "获取服务以及顶级服务")
    @GetMapping("/findOnes")
    @PreAuthorize("hasAuthority('service:get/service/findOnes')")
    public PageResult<SysService> findOnes(){
        List<SysService> list = iSysServiceService.findOnes();
        return PageResult.<SysService>builder().data(list).code(0).count((long)list.size()).build() ;
    }

    /**
     * 删除服务
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('service:delete/service/{id}')")
    @ApiOperation(value = "删除服务")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        try {
            iSysServiceService.delete(id);

        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
        return Result.succeed("操作成功");
    }

    @PreAuthorize("hasAnyAuthority('service:post/saveOrUpdate')")
    @ApiOperation(value = "新增服务")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysService service) {
        try{
            if (service.getId() != null){
                iSysServiceService.update(service);
            }else {
                iSysServiceService.save(service);
            }
            return Result.succeed("操作成功");
        }catch (Exception ex){
            ex.printStackTrace();
            return Result.failed("操作失败");
        }
    }



















}
