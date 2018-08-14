package com.central.user.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.model.common.utils.SysUserUtil;
import com.central.model.user.LoginAppUser;
import com.central.model.user.SysMenu;
import com.central.model.user.SysRole;
import com.central.user.service.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "菜单模块api")
@RequestMapping("/menus")
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	/**
	 * 当前登录用户的菜单
	 * @return
	 */
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

		List<SysMenu> firstLevelMenus = menus.stream().filter(m -> m.getParentId().equals(0L))
				.collect(Collectors.toList());
		firstLevelMenus.forEach(m -> {
			setChild(m, menus);
		});

		return firstLevelMenus;
	}

	private void setChild(SysMenu menu, List<SysMenu> menus) {
		List<SysMenu> child = menus.stream().filter(m -> m.getParentId().equals(menu.getId()))
				.collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(child)) {
			menu.setChild(child);
			// 递归设置子元素，多级菜单支持
			child.parallelStream().forEach(c -> {
				setChild(c, menus);
			});
		}
	}

	/**
	 * 给角色分配菜单
	 * @param userId
	 * @param menuIds
	 */
	@PreAuthorize("hasAuthority('back:menu:granted')")
	@ApiOperation(value = "角色分配菜单")
	@PostMapping("/granted")
	public void setMenuToRole(Long roleId, @RequestBody Set<Long> menuIds) {
		menuService.setMenuToRole(roleId, menuIds);
	}

	/**
	 * 菜单树ztree
	 * 
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('back:menu:granted','back:menu:query')")
	@ApiOperation(value = "获取菜单树列表")
	@GetMapping("/tree")
	public List<SysMenu> findMenuTree() {
		List<SysMenu> all = menuService.findAll();
		List<SysMenu> list = new ArrayList<>();
		setMenuTree(0L, all, list);
		return list;
	}

	/**
	 * 菜单树
	 * @param parentId
	 * @param all
	 * @param list
	 */
	private void setMenuTree(Long parentId, List<SysMenu> all, List<SysMenu> list) {
		all.forEach(menu -> {
			if (parentId.equals(menu.getParentId())) {
				list.add(menu);

				List<SysMenu> child = new ArrayList<>();
				menu.setChild(child);
				setMenuTree(menu.getId(), all, child);
			}
		});
	}

	/**
	 * 获取角色的菜单
	 * @param roleId
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('back:menu:granted','menu:byroleid')")
	@ApiOperation(value = "获取角色菜单列表")
	@GetMapping(params = "roleId")
	public Set<Long> findMenuIdsByRoleId(Long roleId) {
		return menuService.findMenuIdsByRoleId(roleId);
	}

	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:menu:save')")
	@ApiOperation(value = "新增菜单")
	@PostMapping
	public SysMenu save(@RequestBody SysMenu menu) {
		menuService.save(menu);

		return menu;
	}

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:menu:update')")
	@ApiOperation(value = "修改菜单")
	@PutMapping
	public SysMenu update(@RequestBody SysMenu menu) {
		menuService.update(menu);

		return menu;
	}

	/**
	 * 删除菜单
	 * @param id
	 */
	@PreAuthorize("hasAuthority('back:menu:delete')")
	@ApiOperation(value = "删除菜单")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		menuService.delete(id);
	}

	/**
	 * 查询所有菜单
	 * @return
	 */
	@PreAuthorize("hasAuthority('back:menu:query')")
	@ApiOperation(value = "查询所有菜单")
	@GetMapping("/all")
	public List<SysMenu> findAll() {
		List<SysMenu> all = menuService.findAll();
		List<SysMenu> list = new ArrayList<>();
		setSortTable(0L, all, list);

		return list;
	}

	/**
	 * 菜单table
	 * @param parentId
	 * @param all
	 * @param list
	 */
	private void setSortTable(Long parentId, List<SysMenu> all, List<SysMenu> list) {
		all.forEach(a -> {
			if (a.getParentId().equals(parentId)) {
				list.add(a);
				setSortTable(a.getId(), all, list);
			}
		});
	}
	@PreAuthorize("hasAuthority('back:menu:query')")
	@GetMapping("/{id}")
	public SysMenu findById(@PathVariable Long id) {
		return menuService.findById(id);
	}

}
