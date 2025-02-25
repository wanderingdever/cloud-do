package com.easy.common.core.base;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "公共父类VO")
public class BaseVO extends BaseIdVO {

    @Schema(description = "创建人")
    private String createBy;


    @Schema(description = "更新人")
    private String updateBy;


    @Schema(description = "逻辑删除")
    private Boolean del;
}