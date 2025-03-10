package com.easy.server.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 请求数据
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(description = "文件请求数据")
public class FileDTO {

    @Schema(description = "文件名称")
    private String fileName;

    @Schema(description = "文件名字集合")
    private List<String> fileNameList;
}