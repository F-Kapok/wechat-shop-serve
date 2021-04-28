package com.fans.dto;

import lombok.*;

import java.io.Serializable;

/**
 * className: EncryptDTO
 *
 * @author k
 * @version 1.0
 * @description 微信加密体
 * @date 2021-04-22 12:59
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EncryptDTO implements Serializable {

    private static final long serialVersionUID = -20210422125932L;
    private String code;
    private String data;
    private String iv;

}
