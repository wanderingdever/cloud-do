package com.easy.server.bean.dto.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 字典类型入参
 *
 * @author Matt
 */
@Data
@Schema(description = "字典类型入参-新增")
public class DictTypeAddDTO {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "请选择字典类型")
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 字典描述
     */
    @Schema(description = "字典描述")
    private String description;

    /**
     * 是否是系统内置
     */
    @Schema(description = "是否是系统内置")
    private Boolean isSystem;

    /**
     * 是否启用(0否,1-是)
     */
    @Schema(description = "是否启用(0否,1-是)")
    @NotNull(message = "请选择字典类型状态")
    private Boolean enable;
}