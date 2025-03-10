package com.easy.server.bean.dto.config;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 参数配置入参
 * </p>>
 *
 * @author Matt
 */
@Data
@Schema(description = "参数配置入参-新增")
public class ConfigAddDTO {
    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名不能为空")
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空")
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 系统内置（0-否;1-是）
     */
    @NotNull(message = "请选择是否系统内置")
    @Schema(description = "系统内置（0-否;1-是）")
    private Boolean isSystem;

}