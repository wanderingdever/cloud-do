package com.easy.server.bean.dto.notice;


import com.easy.common.core.dto.PageDTO;
import com.easy.server.enums.ArticleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息通知/分页入参
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "消息通知/分页入参")
public class NoticeSearchDTO extends PageDTO {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "状态")
    private ArticleStatus status;

    @Schema(description = "用户ID")
    private String userId;
}