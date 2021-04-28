package com.fans.service.impl;

import com.fans.common.wechat.WeChatModel;
import com.fans.core.exception.http.ParameterException;
import com.fans.dto.EncryptDTO;
import com.fans.entity.User;
import com.fans.repository.UserRepository;
import com.fans.service.IWeChatAuthenticationService;
import com.fans.utils.JsonUtils;
import com.fans.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
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
        WeChatModel weChatModel = getWeChatModel(code);
        log.info("--> {}", JsonUtils.obj2FormattingString(weChatModel));
        assert weChatModel != null;
        return registerUser(weChatModel);
    }

    @Override
    public String getMobile(EncryptDTO encryptDTO) {
        WeChatModel weChatModel = getWeChatModel(encryptDTO.getCode());
        String sessionKey = weChatModel == null ? StringUtils.EMPTY : weChatModel.getSession_key();
        if (StringUtils.isBlank(sessionKey)) {
            return StringUtils.EMPTY;
        }
        byte[] encryptedData = Base64.decodeBase64(encryptDTO.getData());
        byte[] iv = Base64.decodeBase64(encryptDTO.getIv());
        byte[] sessionKeyBase64 = Base64.decodeBase64(sessionKey);
        return decrypt(sessionKeyBase64, iv, encryptedData);
    }

    private String decrypt(byte[] sessionKeyBase64, byte[] iv, byte[] encryptedData) {
        try {
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(sessionKeyBase64, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            //解析解密后的字符串
            return new String(cipher.doFinal(encryptedData), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private WeChatModel getWeChatModel(String code) {
        String url = MessageFormat.format(this.code2SessionUrl, this.appId, this.appSecret, code);
        RestTemplate restTemplate = new RestTemplate();
        WeChatModel weChatModel = null;
        try {
            String result = restTemplate.getForObject(url, String.class);
            weChatModel = JsonUtils.string2Obj(result, WeChatModel.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return weChatModel;
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
