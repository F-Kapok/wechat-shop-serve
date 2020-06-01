package com.fans.service.impl;

import com.fans.dto.TokenGetDTO;
import com.fans.service.IAuthenticationService;
import org.springframework.stereotype.Service;

/**
 * className: AuthenticationServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 21:33
 **/
@Service(value = "iAuthenticationService")
public class AuthenticationServiceImpl implements IAuthenticationService {


    @Override
    public void getTokenByEmail(TokenGetDTO tokenGetDTO) {

    }

    @Override
    public void validateByWeChat(TokenGetDTO tokenGetDTO) {

    }

    @Override
    public void register() {

    }
}
