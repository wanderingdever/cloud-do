package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 字典类型
 * </p>
 *
 * @author Matt
 */
@Schema(description = "字典类型")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dict_type")
public class DictType extends BaseEntity {
    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 字典描述
     */
    @TableField(value = "description")
    @Schema(description = "字典描述")
    private String description;

    /**
     * 是否是系统内置
     */
    @TableField(value = "is_system")
    @Schema(description = "是否是系统内置")
    private Boolean isSystem;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    @Schema(description = "是否启用")
    private Boolean enable;

    @TableField(exist = false)
    @Schema(description = "字典数据")
    private List<DictData> dictDataList;
}