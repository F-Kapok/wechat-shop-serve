package com.fans.service.impl;

import com.fans.core.exception.http.ServerErrorException;
import com.fans.entity.Order;
import com.fans.enums.OrderStatus;
import com.fans.repository.OrderRepository;
import com.fans.service.IWeChatPaymentNotifyService;
import com.fans.service.IWeChatPaymentService;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * className: WeChatPaymentNotifyServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-07-10 23:45
 **/
@Service(value = "iWeChatPaymentNotifyService")
public class WeChatPaymentNotifyServiceImpl implements IWeChatPaymentNotifyService {

    @Resource(name = "iWeChatPaymentService")
    private IWeChatPaymentService iWeChatPaymentService;
    @Resource(type = OrderRepository.class)
    private OrderRepository orderRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class, ServerErrorException.class}, propagation = Propagation.REQUIRED)
    public void processPayNotify(String xml) {
        Map<String, String> notifyMap;
        WXPay wxPay = iWeChatPaymentService.assembleWxPayConfig();
        try {
            notifyMap = WXPayUtil.xmlToMap(xml);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ServerErrorException(9999);
        }
        boolean valid;
        try {
            valid = wxPay.isResponseSignatureValid(notifyMap);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ServerErrorException(9999);
        }
        if (!valid) {
            throw new ServerErrorException(9999);
        }

        String returnCode = notifyMap.get("return_code");
        String orderNo = notifyMap.get("out_trade_no");
        String resultCode = notifyMap.get("result_code");

        String success = "SUCCESS";
        if (!returnCode.equals(success)) {
            throw new ServerErrorException(9999);
        }
        if (!resultCode.equals(success)) {
            throw new ServerErrorException(9999);
        }
        if (orderNo == null) {
            throw new ServerErrorException(9999);
        }
        try {
            this.deal(orderNo);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void deal(String orderNo) {
        Optional<Order> orderInfo = orderRepository.findFirstByOrderNo(orderNo);
        Order order = orderInfo.orElseThrow(() -> new ServerErrorException(9999));
        int res = 0;
        if (order.getStatus().equals(OrderStatus.UNPAID.getCode())
                || order.getStatus().equals(OrderStatus.CANCELED.getCode())) {
            res = this.orderRepository.updateStatusByOrderNo(orderNo, OrderStatus.PAID.getCode());
        }
        if (res != 1) {
            throw new ServerErrorException(9999);
        }
    }
}
