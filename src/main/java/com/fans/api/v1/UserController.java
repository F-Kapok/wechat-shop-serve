package com.fans.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.fans.annotation.ScopeLevel;
import com.fans.common.JsonData;
import com.fans.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * className: UserController
 *
 * @author k
 * @version 1.0
 * @description 用户控制层
 * @date 2020-06-29 15:27
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "iUserService")
    private IUserService iUserService;

    @PostMapping("/wx_info")
    @ScopeLevel
    public JsonData<String> updateUserInfo(@RequestBody JSONObject user) {
        iUserService.updateUserWxInfo(user);
        return JsonData.success("ok");
    }

}
