package com.easy.server.bean.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件信息
 * </p>
 *
 * @author hk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件信息")
public class FileRecordVO implements Serializable {

    private static final long serialVersionUID = -1150334912056490327L;

    @Schema(description = "id")
    private String id;

    @Schema(description = "文件原名称")
    private String fileName;

    @Schema(description = "文件路径")
    private String file;

    @Schema(description = "文件类型")
    private String fileType;
}