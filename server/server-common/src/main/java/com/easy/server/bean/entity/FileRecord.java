package com.easy.server.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseIdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传记录
 * </p>
 *
 * @author matt
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "文件上传记录")
@Data
@TableName(value = "sys_file_record")
public class FileRecord extends BaseIdEntity {

    @Schema(description = "文件原名称")
    @TableField(value = "file_name")
    private String fileName;

    @Schema(description = "文件路径")
    @TableField(value = "file")
    private String file;

    @Schema(description = "文件类型")
    @TableField(value = "file_type")
    private String fileType;

    @Schema(description = "文件大小(字节)")
    @TableField(value = "file_size")
    private String fileSize;

    @Schema(description = "文件md5值")
    @TableField(value = "file_md5")
    private String fileMd5;

    @Schema(description = "下载次数")
    @TableField(value = "downloads")
    private Integer downloads;


}