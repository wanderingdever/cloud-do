package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色->菜单权限关联表
 * </p>
 *
 * @author Matt
 */
@Schema(description = "角色->菜单权限关联表")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_menu")
public class RoleMenu extends BaseEntity {
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @Schema(description = "角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    @Schema(description = "菜单ID")
    private String menuId;
}