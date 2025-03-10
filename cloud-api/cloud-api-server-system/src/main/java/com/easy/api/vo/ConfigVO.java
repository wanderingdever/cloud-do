package com.easy.api.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.common.core.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统参数配置
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "系统参数配置")
public class ConfigVO extends BaseVO {

    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @TableField(value = "config_key")
    @Schema(description = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @Schema(description = "参数键值")
    private String configValue;

    /**
     * 是否是系统内置
     */
    @Schema(description = "是否是系统内置")
    private String isSystem;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private String enable;
}