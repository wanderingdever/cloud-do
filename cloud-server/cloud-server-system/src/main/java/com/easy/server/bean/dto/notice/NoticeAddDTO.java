package com.easy.server.bean.dto.notice;

import com.easy.server.enums.ArticleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 消息通知
 * </p>
 *
 * @author Matt
 */
@Schema(description = "消息通知")
@Data
public class NoticeAddDTO {

    @NotBlank(message = "标题不能为空")
    @Schema(description = "标题")
    private String title;

    @NotBlank(message = "类型不能为空")
    @Schema(description = "类型")
    private String type;

    @NotBlank(message = "富文本不能为空")
    @Schema(description = "富文本内容")
    private String content;


    @NotBlank(message = "文本不能为空")
    @Schema(description = "文本内容")
    private String contentText;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态")
    private ArticleStatus status;

    @NotNull(message = "接收对象不能为空")
    @Schema(description = "接收对象")
    private List<String> userIds;

}