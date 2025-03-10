package com.easy.server.bean.dto.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * 字典数据入参
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "字典数据入参-新增")
public class DictDataAddDTO {

    /**
     * 字典类型ID
     */
    @Schema(description = "字典类型ID")
    @NotBlank(message = "字典类型ID不能为空")
    private String dictTypeId;

    /**
     * 字典排序
     */
    @NotNull(message = "字典排序不能为空")
    @Schema(description = "字典排序")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Schema(description = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @Schema(description = "字典键值")
    private String dictValue;

    /**
     * 样式属性（其他样式扩展）
     */
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @Schema(description = "表格回显样式")
    private String listClass;

    /**
     * 是否启用（0停用;1正常）
     */
    @Schema(description = "是否启用（0停用;1正常）")
    private Boolean enable;

    @Schema(description = "是否删除")
    private Boolean isDel;

}