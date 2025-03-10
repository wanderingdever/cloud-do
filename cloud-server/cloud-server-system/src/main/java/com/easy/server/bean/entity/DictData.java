package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据
 * </p>
 *
 * @author Matt
 */
@Schema(description = "字典数据")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dict_data")
public class DictData extends BaseEntity {
    /**
     * 字典类型ID easy_dict_type=>id
     */
    @TableField(value = "dict_type_id")
    @Schema(description = "字典类型ID easy_dict_type=>id")
    private String dictTypeId;

    /**
     * 字典标签
     */
    @TableField(value = "dict_label")
    @Schema(description = "字典标签")
    private String dictLabel;

    /**
     * 字典键值
     */
    @TableField(value = "dict_value")
    @Schema(description = "字典键值")
    private String dictValue;

    /**
     * 字典排序
     */
    @TableField(value = "dict_sort")
    @Schema(description = "字典排序")
    private Integer dictSort;

    /**
     * 样式属性（其他样式扩展）
     */
    @TableField(value = "css_class")
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
     * 表格回显样式
     */
    @TableField(value = "list_class")
    @Schema(description = "表格回显样式")
    private String listClass;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    @Schema(description = "是否启用")
    private Boolean enable;


}