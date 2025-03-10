package com.easy.auth.bean;

import com.easy.common.core.constant.Constants;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.LoginType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.hutool.crypto.digest.MD5;

@Data
@Schema(title = "登录方式和客户端")
public class LoginTypeAndClient {

    @Schema(title = "登录类型")
    @NotNull(message = "登录类型不能为空")
    private LoginType loginType;

    @Schema(title = "客户端")
    @NotNull(message = "客户端不能为空")
    private AccountClient client;

    @Schema(title = "签名，登录类型和客户端的 md5")
    @NotBlank(message = "签名异常")
    private String sign;

    /**
     * 生成签名
     *
     * @return 签名
     */
    public String generateSign() {
        return MD5.of().digestHex16(loginType.getValue() + Constants.FILE_SEPARATOR + client.getValue());
    }

    /**
     * 校验签名
     *
     * @param sign 签名
     * @return 是否校验通过
     */
    public boolean validateSign(String sign) {
        return generateSign().equals(sign);
    }
}