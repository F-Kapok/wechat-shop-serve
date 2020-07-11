package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.common.wechat.WeChatNotify;
import com.fans.service.IWeChatPaymentNotifyService;
import com.fans.service.IWeChatPaymentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.Map;

/**
 * className: PaymentController
 *
 * @author k
 * @version 1.0
 * @description 支付控制层
 * @date 2020-07-01 21:18
 **/
@RestController
@RequestMapping(value = "/payment")
@Validated
public class PaymentController {

    @Resource(name = "iWeChatPaymentService")
    private IWeChatPaymentService iWeChatPaymentService;
    @Resource(name = "iWeChatPaymentNotifyService")
    private IWeChatPaymentNotifyService iWeChatPaymentNotifyService;

    @PostMapping(value = "/pay/order/{id}")
    @ScopeLevel
    public Map<String, String> preWxOrder(@PathVariable(name = "id") @Positive Long oid) {
        return this.iWeChatPaymentService.preOrder(oid);
    }

    @PostMapping(value = "/notify")
    public String payCallBack(HttpServletRequest request,
                              HttpServletResponse response) {
        try (ServletInputStream inputStream = request.getInputStream()) {
            String xml = WeChatNotify.readNotify(inputStream);
            try {
                iWeChatPaymentNotifyService.processPayNotify(xml);
            } catch (Exception exception) {
                return WeChatNotify.fail();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return WeChatNotify.fail();
        }
        return WeChatNotify.success();
    }
}
