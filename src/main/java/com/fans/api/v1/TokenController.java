package com.fans.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.fans.core.exception.http.NotFountException;
import com.fans.dto.TokenGetDTO;
import com.fans.service.IWeChatAuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * className: TokenController
 *
 * @author k
 * @version 1.0
 * @description 登录控制层
 * @date 2020-05-31 20:47
 **/
@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Resource(name = "iWeChatAuthenticationService")
    private IWeChatAuthenticationService iWeChatAuthenticationService;


    @PostMapping(value = "")
    public JSONObject getToken(@RequestBody @Validated TokenGetDTO tokenGetDTO) {
        JSONObject jsonObject = new JSONObject();
        String token = null;
        switch (tokenGetDTO.getType()) {
            case USER_WX:
                token = iWeChatAuthenticationService.code2Session(tokenGetDTO.getAccount());
                break;
            case USER_EMAIL:
                token = StringUtils.EMPTY;
                break;
            default:
                throw new NotFountException(10003);
        }
        jsonObject.put("token", token);
        return jsonObject;
    }

}
