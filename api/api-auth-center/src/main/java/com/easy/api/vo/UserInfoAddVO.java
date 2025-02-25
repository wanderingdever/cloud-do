package com.easy.api.vo;

import com.easy.common.core.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户账号
 * </p>
 *
 * @author Matt
 */
@Getter
@Setter
@Schema(description = "用户账号")
public class UserInfoAddVO extends UserAccountVO implements Serializable {

    @Schema(description = "账号昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private Sex sex;

}