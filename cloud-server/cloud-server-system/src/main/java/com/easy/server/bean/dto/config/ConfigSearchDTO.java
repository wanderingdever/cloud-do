package com.easy.server.bean.dto.config;

import com.easy.common.core.dto.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置查询/分页入参
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "参数配置查询/分页入参")
public class ConfigSearchDTO extends PageDTO {
    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 系统内置（0-否;1-是）
     */
    @Schema(description = "系统内置（0-否;1-是）")
    private Boolean isSystem;


}