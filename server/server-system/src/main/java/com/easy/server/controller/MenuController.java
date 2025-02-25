package com.easy.server.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.easy.common.core.dto.IdDTO;
import com.easy.server.bean.dto.menu.MenuAddDTO;
import com.easy.server.bean.dto.menu.MenuEditDTO;
import com.easy.server.bean.dto.menu.MenuListDTO;
import com.easy.server.bean.dto.router.RouterVO;
import com.easy.server.bean.vo.MenuTreeVO;
import com.easy.server.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理
 *
 * @author Matt
 */
@RestController
@RequestMapping("/menu")
@Tag(name = "菜单管理")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取用户路由
     *
     * @return Menu
     */
    @PostMapping(value = "/user_router")
    @Operation(summary = "获取用户路由")
    public List<RouterVO> getUserRouter() {
        return menuService.getUserRouter();
    }

    /**
     * 获取树形菜单
     *
     * @param dto 查询条件
     * @return MenuTree
     */
    @PostMapping(value = "/tree")
    @Operation(summary = "树形菜单")
    @SaCheckPermission(value = "system.menu.tree")
    public List<MenuTreeVO> getTreeMenu(@RequestBody MenuListDTO dto) {
        return menuService.getTreeMenu(dto);
    }

    /**
     * 新增菜单
     *
     * @param dto 菜单信息
     */
    @PostMapping(value = "/add")
    @Operation(summary = "新增菜单")
    @SaCheckPermission(value = "system.menu.add")
    public String addMenu(@Valid @RequestBody MenuAddDTO dto) {
        menuService.addMenu(dto);
        return "新增成功";
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单信息
     */
    @PostMapping(value = "/update")
    @Operation(summary = "修改菜单")
    @SaCheckPermission(value = "system.menu.update")
    public String updateMenu(@Valid @RequestBody MenuEditDTO menu) {
        menuService.updateMenu(menu);
        return "修改成功";
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     */
    @PostMapping(value = "/del")
    @Operation(summary = "删除菜单")
    @SaCheckPermission(value = "system.menu.del")
    public String delMenu(@RequestBody IdDTO id) {
        menuService.delMenu(id);
        return "删除成功";
    }
}