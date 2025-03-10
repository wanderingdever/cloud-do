package com.easy.server.bean.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "文件信息")
@NoArgsConstructor
@AllArgsConstructor
public class FileBaseVO implements Serializable {

    @Schema(description = "id")
    private String id;

    @Schema(description = "文件原名称")
    private String originalName;

    @Schema(description = "文件相对路径")
    private String relativePath;
}