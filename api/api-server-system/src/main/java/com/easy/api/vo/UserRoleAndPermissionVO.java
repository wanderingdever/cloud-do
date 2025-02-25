package com.easy.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色和权限信息
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "用户角色和权限信息")
@AllArgsConstructor
public class UserRoleAndPermissionVO implements Serializable {

    @Schema(description = "用户角色")
    private List<RoleVO> roles;

    @Schema(description = "用户权限")
    private List<String> permissions;

}