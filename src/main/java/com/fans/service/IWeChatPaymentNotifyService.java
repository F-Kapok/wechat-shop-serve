package com.fans.service;

/**
 * interfaceName: IWeChatPaymentNotifyService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-07-10 23:44
 **/
public interface IWeChatPaymentNotifyService {
    /**
     * description: 处理微信返回通知信息
     *
     * @param xml 微信返回信息
     * @author k
     * @date 2020/07/10 23:45
     **/
    void processPayNotify(String xml);
}
