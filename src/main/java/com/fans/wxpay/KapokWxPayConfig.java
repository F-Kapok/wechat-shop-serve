package com.fans.wxpay;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * className: WxPayConfig
 *
 * @author k
 * @version 1.0
 * @description 微信支付配置文件
 * @date 2020-07-05 11:47
 **/
@Component(value = "kapokWxPayConfig")
public class KapokWxPayConfig extends WXPayConfig {

    @Value(value = "${wechat.appid}")
    private String appId;

    @Value(value = "${wxpay.machId}")
    private String machId;

    @Value(value = "${wxpay.key}")
    private String key;

    @Value(value = "${wxpay.notifyUrl}")
    private String notifyUrl;

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return machId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getNotifyUrl() {
        return notifyUrl;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}
