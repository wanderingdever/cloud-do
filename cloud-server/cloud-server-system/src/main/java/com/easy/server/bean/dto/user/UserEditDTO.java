package com.easy.server.bean.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户编辑-入参
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户编辑-入参")
public class UserEditDTO extends UserInfoDTO {

    @NotBlank(message = "数据主键不能为空")
    @Schema(description = "id")
    private String id;

}