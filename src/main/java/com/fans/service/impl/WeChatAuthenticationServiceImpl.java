package com.fans.service.impl;

import com.fans.common.WeChatModel;
import com.fans.core.exception.http.ParameterException;
import com.fans.entity.User;
import com.fans.repository.UserRepository;
import com.fans.service.IWeChatAuthenticationService;
import com.fans.utils.JsonUtils;
import com.fans.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * className: WeChatAuthenticationServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 21:53
 **/
@Service(value = "iWeChatAuthenticationService")
@Slf4j
public class WeChatAuthenticationServiceImpl implements IWeChatAuthenticationService {

    @Value(value = "${wechat.appid}")
    private String appId;
    @Value(value = "${wechat.appsecret}")
    private String appSecret;
    @Value(value = "${wechat.code2session}")
    private String code2SessionUrl;

    @Resource(type = UserRepository.class)
    private UserRepository userRepository;

    @Override
    public String code2Session(String code) {
        String url = MessageFormat.format(this.code2SessionUrl, this.appId, this.appSecret, code);
        RestTemplate restTemplate = new RestTemplate();
        WeChatModel weChatModel = null;
        try {
            String result = restTemplate.getForObject(url, String.class);
            weChatModel = JsonUtils.string2Obj(result, WeChatModel.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        log.info("--> {}", JsonUtils.obj2FormattingString(weChatModel));
        assert weChatModel != null;
        return registerUser(weChatModel);
    }

    private String registerUser(WeChatModel weChatModel) {
        String openId = weChatModel.getOpenid();
        if (StringUtils.isBlank(openId)) {
            throw new ParameterException(20004);
        }
        User user = userRepository.findByOpenid(openId);
        if (user == null) {
            User insert = User.builder()
                    .openid(openId)
                    .build();
            userRepository.save(insert);
            return JwtTokenUtils.makeToken(insert.getId());
        }
        //返回jwt令牌
        return JwtTokenUtils.makeToken(user.getId());
    }
}
