package com.central.user.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.central.model.common.PageResult;
import com.central.model.common.Result;
import com.central.model.user.*;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.central.model.common.utils.SysUserUtil;
import com.central.user.service.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "菜单模块api")
@RequestMapping("/menus")
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	// <!--  -->
	/**
	 * 删除菜单
	 * @param id
	 */
	@PreAuthorize("hasAuthority('menu:delete/menus/{id}')")
	@ApiOperation(value = "删除菜单")
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long id) {

		try {
			menuService.delete(id);
			return Result.succeed("操作成功");
		}catch (Exception ex){
			ex.printStackTrace();
			return Result.failed("操作失败");
		}

	}

	@PreAuthorize("hasAuthority('menu:get/menus/{roleId}/menus')")
	@ApiOperation(value = "根据roleId获取对应的菜单")
	@GetMapping("/{roleId}/menus")
	public List<Map<String, Object>> findMenusByRoleId(@PathVariable Long roleId) {
		Set<Long> roleIds = new HashSet<Long>() {{ add(roleId); }};
		List<SysMenu> roleMenus = menuService.findByRoles(roleIds); 		//获取该角色对应的菜单
		List<SysMenu> allMenus = menuService.findAll();						//全部的菜单列表
		List<Map<String, Object>> authTrees = new ArrayList<>();

		Map<Long,SysMenu> roleMenusMap = roleMenus.stream().collect(Collectors.toMap(SysMenu::getId,SysMenu->SysMenu));

		for (SysMenu sysMenu : allMenus) {
			Map<String, Object> authTree = new HashMap<>();
			authTree.put("id",sysMenu.getId());
			authTree.put("name",sysMenu.getName());
			authTree.put("pId",sysMenu.getParentId());
			authTree.put("open",true);
			authTree.put("checked", false);
			if (roleMenusMap.get(sysMenu.getId())!=null){
				authTree.put("checked", true);
			}
			authTrees.add(authTree);
		}
		return authTrees;
	}

	/**
	 * 给角色分配菜单
	 */
	@PreAuthorize("hasAuthority('menu:post/menus/granted')")
	@ApiOperation(value = "角色分配菜单")
	@PostMapping("/granted")
	public Result setMenuToRole(@RequestBody SysMenu sysMenu) {
		System.out.println(sysMenu);
		menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());

		return Result.succeed("操作成功");
	}


	@PreAuthorize("hasAuthority('menu:get/menus/findAlls')")
	@ApiOperation(value = "查询所有菜单")
	@GetMapping("/findAlls")
	public PageResult<SysMenu> findAlls() {
		List<SysMenu> list = menuService.findAll();

		return PageResult.<SysMenu>builder().data(list).code(0).count((long)list.size()).build() ;
	}

	@ApiOperation(value = "获取菜单以及顶级菜单")
	@GetMapping("/findOnes")
	@PreAuthorize("hasAuthority('menu:get/menus/findOnes')")
	public PageResult<SysMenu> findOnes(){
		List<SysMenu> list = menuService.findOnes();
		return PageResult.<SysMenu>builder().data(list).code(0).count((long)list.size()).build() ;
	}

	/**
	 * 添加菜单 或者 更新
	 * @param menu
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('menu:post/menus','menu:put/menus')")
	@ApiOperation(value = "新增菜单")
	@PostMapping("saveOrUpdate")
	public Result saveOrUpdate(@RequestBody SysMenu menu) {

		try{
			if (menu.getId() != null){
				menuService.update(menu);
			}else {
				menuService.save(menu);
			}
			return Result.succeed("操作成功");
		}catch (Exception ex){
			ex.printStackTrace();
			return Result.failed("操作失败");
		}

	}

	/**
	 * 当前登录用户的菜单
	 * @return
	 */
	@PreAuthorize("hasAuthority('menu:get/menus/current')")
	@GetMapping("/current")
	@ApiOperation(value = "查询当前用户菜单")
	public List<SysMenu> findMyMenu() {
		LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
		Set<SysRole> roles = loginAppUser.getSysRoles();
		if (CollectionUtils.isEmpty(roles)) {
			return Collections.emptyList();
		}

		List<SysMenu> menus = menuService
				.findByRoles(roles.parallelStream().map(SysRole::getId).collect(Collectors.toSet()));


		List<SysMenu> sysMenus = TreeBuilder(menus);

		return sysMenus;
	}

	/**
	 * 两层循环实现建树
	 * @param sysMenus
	 * @return
	 */
	public static List<SysMenu> TreeBuilder(List<SysMenu> sysMenus){
		List<SysMenu> menus = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : sysMenus){
			if (ObjectUtils.equals(-1L,sysMenu.getParentId())){
				menus.add(sysMenu);
			}
			for (SysMenu menu :sysMenus){
				if (menu.getParentId().equals(sysMenu.getId())){
					if (sysMenu.getSubMenus() == null){
						sysMenu.setSubMenus(new ArrayList<>());
					}
					sysMenu.getSubMenus().add(menu);
				}
			}
		}
		return menus;
	}

}
