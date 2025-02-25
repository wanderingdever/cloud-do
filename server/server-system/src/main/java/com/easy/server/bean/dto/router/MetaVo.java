package com.easy.server.bean.dto.router;

import com.easy.common.util.lang.StringUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由显示信息
 *
 * @author Matt
 */
@Data
@Schema(description = "路由显示信息")
public class MetaVo implements Serializable {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    @Schema(description = "设置该路由在侧边栏和面包屑中展示的名字")
    private String title;
    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    @Schema(description = "设置该路由的图标，对应路径src/assets/icons/svg")
    private String icon;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    @Schema(description = "设置该路由在侧边栏和面包屑中展示的名字")
    private Boolean isHide;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    @Schema(description = "设置为true，则不会被 <keep-alive>缓存")
    private Boolean isKeepAlive;

    /**
     * 是否是内联
     */
    @Schema(description = "是否是内联")
    private Boolean isIframe;

    /**
     * 是否固定
     */
    @Schema(description = "是否固定")
    private Boolean isAffix;

    /**
     * 内链地址（http(s)://开头）
     */
    @Schema(description = "内链地址（http(s)://开头）")
    private String isLink;


    public MetaVo() {
    }

    public MetaVo(String title, String icon, Boolean isHide, Boolean isKeepAlive, Boolean isIframe,
                  Boolean isAffix, String link) {
        this.title = title;
        this.icon = icon;
        this.isHide = isHide;
        this.isKeepAlive = isKeepAlive;
        this.isAffix = isAffix;
        this.isIframe = isIframe;
        if (StringUtils.isHttp(link)) {
            this.isLink = link;
        } else {
            this.isLink = "";
        }
    }
}