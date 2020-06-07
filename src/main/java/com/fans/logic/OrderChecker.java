package com.fans.logic;

import com.fans.bo.SkuOrderBO;
import com.fans.core.exception.http.ParameterException;
import com.fans.dto.OrderDTO;
import com.fans.dto.SkuInfoDTO;
import com.fans.entity.OrderSku;
import com.fans.entity.Sku;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * className: OrderChecker
 *
 * @author k
 * @version 1.0
 * @description 订单检验业务
 * @date 2020-06-06 23:26
 * //
 **/
//@Component(value = "orderChecker")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderChecker {

    private final OrderDTO orderDTO;
    private final List<Sku> serverSkuList;
    private final CouponChecker couponChecker;
    private final Integer maxSkuLimit;
    @Getter
    private final List<OrderSku> orderSkuList = Lists.newArrayList();

    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList, CouponChecker couponChecker, Integer maxSkuLimit) {
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
    }

    public String getLeaderImg() {
        return serverSkuList.get(0).getImg();
    }

    public String getLeaderTitle() {
        return serverSkuList.get(0).getTitle();
    }

    public Integer getTotalCount() {
        return this.orderDTO.getSkuInfoList()
                .stream()
                .map(SkuInfoDTO::getCount)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public void isOk() {
        BigDecimal serverTotalPrice = BigDecimal.ZERO;
        List<SkuOrderBO> skuOrderBOList = Lists.newArrayList();
        skuNotOnSale(orderDTO.getSkuInfoList().size(), serverSkuList.size());
        for (int i = 0; i < serverSkuList.size(); i++) {
            Sku sku = serverSkuList.get(i);
            SkuInfoDTO skuInfoDTO = orderDTO.getSkuInfoList().get(i);
            containsSoldOutSku(sku);
            beyondSkuStock(sku, skuInfoDTO);
            beyondMaxSkuLimit(skuInfoDTO);
            serverTotalPrice = serverTotalPrice.add(calculateSkuOrderPrice(sku, skuInfoDTO));
            skuOrderBOList.add(new SkuOrderBO(sku, skuInfoDTO));
            orderSkuList.add(new OrderSku(sku, skuInfoDTO));
        }
        totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);
        //优惠券校验
        if (couponChecker != null) {
            couponChecker.isOk();
            couponChecker.canBeUsed(skuOrderBOList, serverTotalPrice);
            couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    /**
     * description: 比较服务端与前端总价是否相等
     *
     * @param orderTotalPrice  前端传入的总价
     * @param serverTotalPrice 服务端计算的总价
     * @author k
     * @date 2020/06/07 16:04
     **/
    private void totalPriceIsOk(BigDecimal orderTotalPrice, BigDecimal serverTotalPrice) {
        if (orderTotalPrice.compareTo(serverTotalPrice) != 0) {
            throw new ParameterException(50005);
        }
    }

    private BigDecimal calculateSkuOrderPrice(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() <= 0) {
            throw new ParameterException(50007);
        }
        return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
    }

    /**
     * description: 是否在线
     *
     * @param count1 前端传入的sku数量
     * @param count2 服务端sku数量
     * @author k
     * @date 2020/06/07 15:39
     **/
    private void skuNotOnSale(int count1, int count2) {
        if (count1 != count2) {
            throw new ParameterException(50002);
        }
    }

    /**
     * description: 是否售罄
     *
     * @param sku 服务端sku数据
     * @author k
     * @date 2020/06/07 15:39
     **/
    private void containsSoldOutSku(Sku sku) {
        if (sku.getStock() == 0) {
            throw new ParameterException(50001);
        }
    }

    /**
     * description: 是否超卖
     *
     * @param sku        服务端sku数据
     * @param skuInfoDTO 前端sku数据
     * @author k
     * @date 2020/06/07 15:41
     **/
    private void beyondSkuStock(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (sku.getStock() < skuInfoDTO.getCount()) {
            throw new ParameterException(50003);
        }
    }

    /**
     * description: 是否超过购买最大数量
     *
     * @param skuInfoDTO 前端购买数据
     * @author k
     * @date 2020/06/07 15:43
     **/
    private void beyondMaxSkuLimit(SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() > maxSkuLimit) {
            throw new ParameterException(50004);
        }
    }

}
