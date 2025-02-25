package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import com.easy.server.enums.ArticleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息通知
 * </p>
 *
 * @author Matt
 */
@Schema(description = "消息通知")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_notice")
public class Notice extends BaseEntity {

    @TableField(value = "title")
    @Schema(description = "标题")
    private String title;

    @TableField(value = "type")
    @Schema(description = "类型")
    private String type;

    @TableField(value = "content")
    @Schema(description = "富文本内容")
    private String content;

    @Schema(description = "纯文本内容")
    private String contentText;

    @TableField(value = "status")
    @Schema(description = "文章状态")
    private ArticleStatus status;

}