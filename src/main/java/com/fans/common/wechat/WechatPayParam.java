package com.fans.common.wechat;

import lombok.*;

import java.io.Serializable;

/**
 * className: WechatPayParam
 *
 * @author k
 * @version 1.0
 * @description 微信支付入参
 * @date 2020-07-05 14:10
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class WechatPayParam implements Serializable {

    private static final long serialVersionUID = -20200705141036L;
    /**
     * 商品描述 例如：腾讯充值中心-QQ会员充值
     */
    private String body;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 设备号 自定义
     */
    private String device_info;
    /**
     * 标价币种 例如：CNY
     */
    private String fee_type;
    /**
     * 交易类型 例如：JSAPI   -JSAPI支付
     */
    private String trade_type;
    /**
     * 标价金额 单位：分
     */
    private String total_fee;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 终端Ip
     */
    private String spbill_create_ip;
    /**
     * 回调地址
     */
    private String notify_url;

}
