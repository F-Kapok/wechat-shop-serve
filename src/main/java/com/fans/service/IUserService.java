package com.fans.service;

import com.alibaba.fastjson.JSONObject;

/**
 * interfaceName: IUserService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 15:29
 **/
public interface IUserService {

    void updateUserWxInfo(JSONObject weChatUserInfo);
}
