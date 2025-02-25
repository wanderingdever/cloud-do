package com.easy.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.dto.IdListDTO;
import com.easy.server.bean.dto.role.RoleDTO;
import com.easy.server.bean.dto.role.RoleEditDTO;
import com.easy.server.bean.dto.role.RoleSearchDTO;
import com.easy.server.bean.vo.RoleInfoVO;
import com.easy.server.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理
 * </p>
 *
 * @author Matt
 */
@RestController
@RequestMapping("/role")
@Tag(name = "角色管理")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping(value = "/add")
    @Operation(summary = "新增角色")
    @SaCheckPermission(value = "system.role.add")
    public void add(@RequestBody @Valid RoleDTO dto) {
        roleService.addRole(dto);
    }

    @PostMapping(value = "/update")
    @Operation(summary = "编辑角色")
    @SaCheckPermission(value = "system.role.update")
    public void update(@RequestBody @Valid RoleEditDTO dto) {
        roleService.updateRole(dto);
    }

    @PostMapping(value = "/del")
    @Operation(summary = "删除角色")
    @SaCheckPermission(value = "system.role.del")
    public void del(@RequestBody IdListDTO dto) {
        roleService.delRole(dto);
    }

    @PostMapping("/page")
    @Operation(summary = "角色分页查询")
    @SaCheckPermission(value = "system.role.page")
    public Page<RoleInfoVO> page(@RequestBody RoleSearchDTO dto) {
        return roleService.page(dto);
    }

    @PostMapping(value = "/role_menu_ids")
    @Operation(summary = "角色菜单集合")
    public List<String> roleMenuIds(@RequestBody IdDTO dto) {
        return roleService.roleMenuIds(dto);
    }
}