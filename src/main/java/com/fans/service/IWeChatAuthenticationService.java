package com.fans.service;

/**
 * interfaceName: IWeChatAuthenticationService
 *
 * @author k
 * @version 1.0
 * @description 微信认证服务层
 * @date 2020-05-31 21:34
 **/
public interface IWeChatAuthenticationService {

    String code2Session(String code);
}
