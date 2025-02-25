package com.easy.server.bean.dto.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置入参
 * </p>>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "参数配置入参-编辑")
public class ConfigEditDTO extends ConfigAddDTO {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotBlank(message = "数据ID不能为空")
    private String id;

}