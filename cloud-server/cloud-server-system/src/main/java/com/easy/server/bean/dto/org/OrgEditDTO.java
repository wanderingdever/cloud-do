package com.easy.server.bean.dto.org;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "组织编辑-入参")
public class OrgEditDTO extends OrgDTO {

    @Schema(description = "组织id")
    private String id;
}