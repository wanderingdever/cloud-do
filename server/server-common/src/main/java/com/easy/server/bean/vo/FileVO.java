package com.easy.server.bean.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 文件数据
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件信息")
@NoArgsConstructor
@AllArgsConstructor
public class FileVO extends FileBaseVO {

    @Schema(description = "文件绝对路径")
    private String absolutePath;

    @Schema(description = "文件host")
    private String host;

    @Schema(description = "文件大小")
    private String fileSize;

    public FileVO(String originalName, String relativePath, String host, long size) {
        this.setOriginalName(originalName);
        this.setOriginalName(relativePath);
        this.host = host;
        this.fileSize = String.valueOf(size);
    }
}