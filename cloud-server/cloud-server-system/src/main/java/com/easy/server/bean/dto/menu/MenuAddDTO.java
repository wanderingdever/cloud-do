package com.easy.server.bean.dto.menu;

import com.easy.server.enums.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 菜单入参
 * </p>
 *
 * @author Matt
 */
@Schema(description = "菜单入参-新增")
@Data
public class MenuAddDTO {

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private String parentId;

    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;

    /**
     * 重定向
     */
    @Schema(description = "重定向")
    private String redirect;

    /**
     * 菜单类型（0-目录;1-页面;2-按钮）
     */
    @Schema(description = "菜单类型（0目录;1页面;2按钮）")
    private MenuType menuType;

    /**
     * 是否内嵌(0-否;1-是)
     */
    @Schema(description = "是否内嵌(0-否;1-是),")
    private Boolean isIframe;

    /**
     * 是否为外链
     */
    @Schema(description = "是否为外链")
    private Boolean isLink;
    /**
     * 地址
     */
    @Schema(description = "地址")
    private String link;

    /**
     * 菜单是否显示
     */
    @Schema(description = "菜单是否显示")
    private Boolean isHide;

    /**
     * 是否缓存
     */
    @Schema(description = "是否缓存")
    private Boolean isKeepAlive;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 菜单是否启用（0-否;1-是）
     */
    @Schema(description = "菜单是否启用（0-否;1-是）")
    private Boolean enable;

}