package com.easy.server.bean.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单集合入参
 * </p>
 *
 * @author Matt
 */
@Schema(description = "菜单集合入参")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuListDTO {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "菜单名字")
    private String menuName;
}