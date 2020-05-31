package com.fans.service;

import com.fans.dto.TokenGetDTO;

/**
 * interfaceName: IAuthenticationService
 *
 * @author k
 * @version 1.0
 * @description 认证服务层
 * @date 2020-05-31 21:31
 **/
public interface IAuthenticationService {

    void getTokenByEmail(TokenGetDTO tokenGetDTO);

    void validateByWeChat(TokenGetDTO tokenGetDTO);

    void register();
}
