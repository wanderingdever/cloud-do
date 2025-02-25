package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织信息
 * </p>
 *
 * @author Matt
 */
@Schema(description = "组织信息")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_org")
public class Org extends BaseEntity {
    /**
     * 上级id
     */
    @TableField(value = "org_parent_id")
    @Schema(description = "上级id")
    private String orgParentId;

    /**
     * 组织名称
     */
    @TableField(value = "org_name")
    @Schema(description = "组织名称")
    private String orgName;

    /**
     * 组织简称
     */
    @TableField(value = "org_short_name")
    @Schema(description = "组织简称")
    private String orgShortName;

    /**
     * 组织级别
     */
    @TableField(value = "org_level")
    @Schema(description = "组织级别")
    private String orgLevel;

    /**
     * 组织类型
     */
    @TableField(value = "org_type")
    @Schema(description = "组织类型")
    private String orgType;

    /**
     * 组织性质
     */
    @TableField(value = "org_nature")
    @Schema(description = "组织性质")
    private String orgNature;

    /**
     * 机构编码
     */
    @TableField(value = "org_code")
    @Schema(description = "机构编码")
    private String orgCode;

    /**
     * 组织标签
     */
    @TableField(value = "org_tag")
    @Schema(description = "组织标签")
    private String orgTag;

    /**
     * 省
     */
    @TableField(value = "org_province")
    @Schema(description = "省")
    private String orgProvince;

    /**
     * 市
     */
    @TableField(value = "org_city")
    @Schema(description = "市")
    private String orgCity;

    /**
     * 区
     */
    @TableField(value = "org_district")
    @Schema(description = "区")
    private String orgDistrict;

    /**
     * 区划代码集
     */
    @TableField(value = "org_area_code")
    @Schema(description = "区划代码集")
    private String orgAreaCode;

    /**
     * 详细地址
     */
    @TableField(value = "org_address")
    @Schema(description = "详细地址")
    private String orgAddress;

    /**
     * 机构简介
     */
    @TableField(value = "org_desc")
    @Schema(description = "机构简介")
    private String orgDesc;

    /**
     * logo
     */
    @TableField(value = "org_logo")
    @Schema(description = "logo")
    private String orgLogo;

    /**
     * 顺序
     */
    @TableField(value = "org_sort")
    @Schema(description = "顺序")
    private Integer orgSort;

    /**
     * 组织状态
     */
    @TableField(value = "org_status")
    @Schema(description = "组织状态")
    private String orgStatus;

}