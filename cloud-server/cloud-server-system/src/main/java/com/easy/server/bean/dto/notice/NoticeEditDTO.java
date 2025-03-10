package com.easy.server.bean.dto.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息通知
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息通知")
@Data
public class NoticeEditDTO extends NoticeAddDTO {

    @Schema(description = "主键")
    @NotBlank(message = "数据ID不能为空")
    private String id;

}