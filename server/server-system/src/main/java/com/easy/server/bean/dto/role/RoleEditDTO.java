package com.easy.server.bean.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色编辑-请求参数")
public class RoleEditDTO extends RoleDTO {

    @NotBlank(message = "id不能为空")
    @Schema(title = "id")
    private String id;
}