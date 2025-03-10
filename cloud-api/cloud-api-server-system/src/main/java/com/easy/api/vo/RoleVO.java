package com.easy.api.vo;

import com.easy.common.core.base.BaseVO;
import com.easy.common.core.enums.AuthorityLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色信息
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色信息")
@Data
public class RoleVO extends BaseVO implements Serializable {
    /**
     * 角色名字
     */
    @Schema(description = "角色名字")
    private String roleName;

    /**
     * 角色key
     */
    @Schema(description = "角色key")
    private String roleKey;

    /**
     * 权限级别
     */
    @Schema(description = "权限级别")
    private AuthorityLevel authorityLevel;

    /**
     * 角色所属机构ID
     */
    @Schema(description = "角色所属机构ID")
    private String orgId;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer roleSort;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enable;

}