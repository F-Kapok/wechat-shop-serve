package com.fans.service.impl;

import com.fans.common.CommonUtils;
import com.fans.common.LocalUser;
import com.fans.common.wechat.WechatPayParam;
import com.fans.core.exception.http.ForbiddenException;
import com.fans.core.exception.http.ParameterException;
import com.fans.core.exception.http.ServerErrorException;
import com.fans.entity.Order;
import com.fans.service.IOrderService;
import com.fans.service.IWeChatPaymentService;
import com.fans.utils.NetWorkUtils;
import com.fans.utils.ObjectUtils;
import com.fans.wxpay.KapokWxPayConfig;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * className: WeChatPaymentServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-07-01 21:24
 **/
@Service(value = "iWeChatPaymentService")
public class WeChatPaymentServiceImpl implements IWeChatPaymentService {

    @Resource(name = "kapokWxPayConfig")
    private KapokWxPayConfig kapokWxPayConfig;
    @Resource()
    private IOrderService iOrderService;

    @Value(value = "${kapok.order.pay-callback-host}")
    private String payCallbackHost;

    @Value(value = "${kapok.order.pay-callback-path}")
    private String payCallbackPath;

    @Override
    public Map<String, String> preOrder(Long oid) {
        Long uid = LocalUser.getUser().getId();
        Optional<Order> orderInfo = iOrderService.getOrderDetail(oid);
        Order order = orderInfo.orElseThrow(() -> new ForbiddenException(50009));
        if (order.needCancel()) {
            throw new ForbiddenException(50010);
        }
        WXPay wxPay = assembleWxPayConfig();
        Map<String, String> wxOrder;
        try {
            wxOrder = wxPay.unifiedOrder(makePreOrderParams(order.getOrderNo(), order.getFinalTotalPrice()));
        } catch (Exception exception) {
            throw new ServerErrorException(9999);
        }
        if (unifiedOrderSuccess(wxOrder)) {
            iOrderService.updateOrderPrepayId(order.getId(), wxOrder.get("prepay_id"));
        }
        return this.makePaySignature(wxOrder);
    }

    public Map<String, String> makePaySignature(Map<String, String> wxOrder) {
        Map<String, String> weChatPayMap = Maps.newHashMap();
        String packages = "prepay_id=" + wxOrder.get("prepay_id");
        weChatPayMap.put("appId", kapokWxPayConfig.getAppID());
        String timeStamp = String.valueOf(DateTime.now().getMillis());
        weChatPayMap.put("timeStamp", timeStamp.substring(0, timeStamp.length() - 3));
        weChatPayMap.put("nonceStr", WXPayUtil.generateNonceStr());
        weChatPayMap.put("package", packages);
        weChatPayMap.put("signType", WXPayConstants.MD5);
        String sign;
        try {
            sign = WXPayUtil.generateSignature(weChatPayMap, kapokWxPayConfig.getKey(), WXPayConstants.SignType.MD5);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ServerErrorException(9999);
        }
        Map<String, String> result = Maps.newHashMap(weChatPayMap);
        result.put("paySign", sign);
        result.remove("appId");
        return result;
    }

    public static void main(String[] args) {
        System.out.println(WXPayConstants.HMACSHA256);
    }

    private Boolean unifiedOrderSuccess(Map<String, String> wxOrder) {
        if (!wxOrder.get("return_code").equals("SUCCESS") || !wxOrder.get("result_code").equals("SUCCESS")) {
            throw new ParameterException(10007);
        }
        return Boolean.TRUE;
    }

    /**
     * description: 拼装支付入参
     *
     * @param orderNo          商户订单编号
     * @param serverFinalPrice 商户订单总价
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author k
     * @date 2020/07/05 14:46
     **/
    private Map<String, String> makePreOrderParams(String orderNo, BigDecimal serverFinalPrice) {
        String payCallBackUrl = this.payCallbackHost + this.payCallbackPath;
        Map<String, Object> map = ObjectUtils.object2Map(WechatPayParam.builder()
                .body("kapok")
                .out_trade_no(orderNo)
                .device_info("kapok")
                .fee_type("CNY")
                .trade_type("JSAPI")
                .total_fee(CommonUtils.yuan2FenPlainString(serverFinalPrice))
                .openid(LocalUser.getUser().getOpenid())
                .spbill_create_ip(NetWorkUtils.getIpAddress())
                .notify_url(payCallBackUrl)
                .build());
        Map<String, String> result = Maps.newHashMap();
        map.forEach((key, value) -> result.put(key, (String) value));
        return result;
    }

    @Override
    public WXPay assembleWxPayConfig() {
        WXPay wxPay;
        try {
            wxPay = new WXPay(kapokWxPayConfig);
        } catch (Exception exception) {
            throw new ServerErrorException(9999);
        }
        return wxPay;
    }
}
