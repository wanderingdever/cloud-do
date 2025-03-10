package com.easy.server.bean.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easy.common.core.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "用户信息-入参")
public class UserInfoDTO extends UserDTO {

    @Schema(description = "机构ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "机构信息不能为空")
    private String orgId;

    @Schema(description = "账号昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "性别")
    @TableField("sex")
    private Sex sex;

    @Schema(description = "角色")
    private List<String> roleList;

}