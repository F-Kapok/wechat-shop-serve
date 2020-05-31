package com.fans.dto;

import com.fans.annotation.TokenPassword;
import com.fans.enums.LoginType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * className: TokenGetDTO
 *
 * @author k
 * @version 1.0
 * @description 登录入参
 * @date 2020-05-31 20:50
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TokenGetDTO implements Serializable {

    private static final long serialVersionUID = -20200531205032L;
    @NotBlank(message = "account不允许为空")
    private String account;
    @TokenPassword(max = 30, message = "{token.password}")
    private String password;

    private LoginType type;
}
