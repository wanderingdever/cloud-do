package com.easy.server.bean.vo.org;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 机构用户组成的树形
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "机构用户组成树形-响应参数")
public class OrgUserTreeVO implements Serializable {

    @Schema(description = "数据ID")
    private String id;

    @Schema(description = "父节点ID")
    private String parentId;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "数据类型,机构:org,用户:user")
    private String type;

    @Schema(description = "子节点")
    private Boolean hasChildren;
}