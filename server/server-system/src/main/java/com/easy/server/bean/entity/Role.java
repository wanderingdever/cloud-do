package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import com.easy.common.core.enums.AuthorityLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息
 * </p>
 *
 * @author Matt
 */
@Schema(description = "角色信息")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class Role extends BaseEntity {
    /**
     * 角色名字
     */
    @TableField(value = "role_name")
    @Schema(description = "角色名字")
    private String roleName;

    /**
     * 角色key
     */
    @TableField(value = "role_key")
    @Schema(description = "角色key")
    private String roleKey;

    /**
     * 权限级别
     */
    @TableField(value = "authority_level")
    @Schema(description = "权限级别")
    private AuthorityLevel authorityLevel;

    /**
     * 角色所属机构ID
     */
    @TableField(value = "org_id")
    @Schema(description = "角色所属机构ID")
    private String orgId;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    @Schema(description = "显示顺序")
    private Integer roleSort;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    @Schema(description = "是否启用")
    private Boolean enable;

}