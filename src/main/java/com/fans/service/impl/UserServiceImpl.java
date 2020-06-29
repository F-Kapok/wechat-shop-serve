package com.fans.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fans.common.LocalUser;
import com.fans.entity.User;
import com.fans.repository.UserRepository;
import com.fans.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * className: UserServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 15:31
 **/
@Service(value = "iUserService")
public class UserServiceImpl implements IUserService {

    @Resource(type = UserRepository.class)
    private UserRepository userRepository;

    @Override
    public void updateUserWxInfo(JSONObject weChatUserInfo) {
        User user = userRepository.findFirstById(LocalUser.getUser().getId());
        user.setNickname(weChatUserInfo.get("nickName").toString());
        user.setWxProfile(weChatUserInfo);
        userRepository.save(user);
    }
}
