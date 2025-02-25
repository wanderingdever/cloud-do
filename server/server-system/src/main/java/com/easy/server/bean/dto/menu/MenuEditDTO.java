package com.easy.server.bean.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单入参
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单入参-编辑")
@Data
public class MenuEditDTO extends MenuAddDTO {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotBlank(message = "数据ID不能为空")
    private String id;


}