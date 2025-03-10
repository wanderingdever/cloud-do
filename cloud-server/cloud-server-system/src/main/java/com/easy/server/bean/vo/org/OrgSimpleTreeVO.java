package com.easy.server.bean.vo.org;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组织树查询-简单响应参数
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "组织树查询-简单响应参数")
public class OrgSimpleTreeVO implements Serializable {

    @Schema(description = "主键ID")
    private String id;

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
     * 机构编码
     */
    @Schema(description = "机构编码")
    private String orgCode;

    /**
     * 组织状态
     */
    @Schema(description = "组织状态")
    private String orgStatus;

    @Schema(description = "下级")
    private List<OrgSimpleTreeVO> children;
}