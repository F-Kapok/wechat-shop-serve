package com.fans.service;

import com.github.wxpay.sdk.WXPay;

import java.util.Map;

/**
 * interfaceName: IWechatPaymentService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-07-01 21:23
 **/
public interface IWeChatPaymentService {

    Map<String, String> preOrder(Long oid);

    WXPay assembleWxPayConfig();
}
