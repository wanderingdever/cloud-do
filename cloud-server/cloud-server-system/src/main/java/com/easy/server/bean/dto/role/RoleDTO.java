package com.easy.server.bean.dto.role;

import com.easy.common.core.enums.AuthorityLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 角色信息
 *
 * @author Matt
 */
@Data
@Schema(description = "角色入参")
public class RoleDTO {

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String roleName;

    @NotBlank(message = "角色权限标识不能为空")
    @Schema(description = "角色权限标识")
    private String roleKey;

    @NotNull(message = "角色权限标识不能为空")
    @Schema(description = "权限级别")
    private AuthorityLevel authorityLevel;

    @NotBlank(message = "角色所属机构不能为空")
    @Schema(description = "角色所属机构ID")
    private String orgId;

    @Schema(description = "显示顺序")
    private Integer roleSort;

    @Schema(description = "是否启用（0停用;1正常）")
    private Boolean enable;

    @Schema(description = "菜单ID数组")
    private List<String> menuList;

}