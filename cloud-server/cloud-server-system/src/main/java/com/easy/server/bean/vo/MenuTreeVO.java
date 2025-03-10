package com.easy.server.bean.vo;

import com.easy.common.core.base.BaseVO;
import com.easy.server.enums.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单")
@Data
public class MenuTreeVO extends BaseVO {


    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
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

    @Schema(description = "是否固定")
    private Boolean isAffix;

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

    /**
     * 子菜单
     */
    @Schema(description = "子菜单")
    private List<MenuTreeVO> children;
}