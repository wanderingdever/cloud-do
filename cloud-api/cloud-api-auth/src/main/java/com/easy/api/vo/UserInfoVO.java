package com.easy.api.vo;

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
public class UserInfoVO extends UserInfoExpandVO implements Serializable {

    @Schema(description = "主键ID")
    private String id;

}