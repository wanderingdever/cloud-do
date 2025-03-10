package com.easy.server.bean.dto;

import com.easy.common.core.dto.PageDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 文件信息
 * </p>
 *
 * @author hk
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件信息")
public class FileQueryDTO extends PageDTO {

    @Schema(description = "主键ids")
    private List<String> ids;

    @Schema(description = "文件名")
    private String fileName;

    @Schema(description = "文件路径")
    private String filePath;

    @Schema(description = "文件类型")
    private String fileType;

}