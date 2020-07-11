package com.fans.common.wechat;

import lombok.*;

import java.io.Serializable;

/**
 * className: WeChatModel
 *
 * @author k
 * @version 1.0
 * @description 微信认证返回参数
 * @date 2020-05-31 23:17
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WeChatModel implements Serializable {

    private static final long serialVersionUID = -20200531231719L;

    private String openid;

    private String session_key;

    private String unionid;

    private Integer errcode;

    private String errmsg;

}
