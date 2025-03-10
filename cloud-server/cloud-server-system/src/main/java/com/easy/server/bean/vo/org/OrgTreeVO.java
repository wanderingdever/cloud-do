package com.easy.server.bean.vo.org;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.common.core.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 组织树查询-响应参数
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "组织树查询-详情响应参数")
public class OrgTreeVO extends BaseVO {


    @Schema(description = "上级id")
    private String orgParentId;
    /**
     * 组织名称
     */
    @Schema(description = "组织名称")
    private String orgName;

    /**
     * 组织简称
     */
    @Schema(description = "组织简称")
    private String orgShortName;

    /**
     * 组织级别
     */
    @Schema(description = "组织级别")
    private String orgLevel;

    /**
     * 组织类型
     */
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
    @Schema(description = "机构编码")
    private String orgCode;

    /**
     * 组织标签
     */
    @Schema(description = "组织标签")
    private String orgTag;

    /**
     * 省
     */
    @Schema(description = "省")
    private String orgProvince;

    /**
     * 市
     */
    @Schema(description = "市")
    private String orgCity;

    /**
     * 区
     */
    @Schema(description = "区")
    private String orgDistrict;

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
    @Schema(description = "机构简介")
    private String orgDesc;

    /**
     * logo
     */
    @Schema(description = "logo")
    private String orgLogo;

    /**
     * 顺序
     */
    @Schema(description = "顺序")
    private Integer orgSort;

    /**
     * 组织状态
     */
    @Schema(description = "组织状态")
    private String orgStatus;

    @Schema(description = "下级")
    private List<OrgTreeVO> children;
}