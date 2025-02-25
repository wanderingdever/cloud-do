package com.easy.common.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ID请求参数ID请求参数
 * </p>
 *
 * @author Matt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "ID请求参数")
public class IdDTO {

    @NotBlank(message = "id不能为空")
    @Schema(title = "id")
    private String id;
}