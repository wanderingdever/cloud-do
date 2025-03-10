package com.easy.server.bean.dto.dict;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据入参
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典数据入参-编辑")
public class DictDataEditDTO extends DictDataAddDTO {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotBlank(message = "数据ID不能为空")
    private String id;
}