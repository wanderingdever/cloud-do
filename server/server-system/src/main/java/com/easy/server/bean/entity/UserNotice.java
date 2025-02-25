package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseEntity;
import com.easy.server.enums.ArticleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户消息通知关联
 * </p>
 *
 * @author Matt
 */
@Schema(description = "用户消息通知关联")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_notice")
public class UserNotice extends BaseEntity {

    @TableField(value = "user_id")
    @Schema(description = "用户ID")
    private String userId;

    @TableField(value = "notice_id")
    @Schema(description = "消息ID")
    private String noticeId;

    @TableField(value = "status")
    @Schema(description = "状态")
    private ArticleStatus status;

    public UserNotice(String userId, String noticeId, ArticleStatus status) {
        this.userId = userId;
        this.noticeId = noticeId;
        this.status = status;
    }
}