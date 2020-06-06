package com.fans.service.impl;

import com.fans.core.exception.http.NotFountException;
import com.fans.core.exception.http.ParameterException;
import com.fans.dto.OrderDTO;
import com.fans.dto.SkuInfoDTO;
import com.fans.entity.Coupon;
import com.fans.entity.Sku;
import com.fans.entity.UserCoupon;
import com.fans.logic.CouponChecker;
import com.fans.repository.CouponRepository;
import com.fans.repository.UserCouponRepository;
import com.fans.service.IOrderService;
import com.fans.service.ISkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: OrderServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:05
 **/
@Service(value = "iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Resource(name = "iSkuService")
    private ISkuService iSkuService;
    @Resource(type = CouponRepository.class)
    private CouponRepository couponRepository;
    @Resource(type = UserCouponRepository.class)
    private UserCouponRepository userCouponRepository;
    @Resource(name = "couponChecker")
    private CouponChecker couponChecker;

    @Override
    public void isOk(Long userId, OrderDTO orderDTO) {
        if (orderDTO.getFinalTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ParameterException(50011);
        }
        List<Long> skuIdList = orderDTO.getSkuInfoList().stream().map(SkuInfoDTO::getId).collect(Collectors.toList());

        List<Sku> skuList = iSkuService.getSkuListByIds(skuIdList);

        Long couponId = orderDTO.getCouponId();
        if (couponId != null) {
            Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new NotFountException(40004));
            UserCoupon userCoupon = userCouponRepository.findFirstByUserIdAndCouponId(userId, couponId).orElseThrow(() -> new NotFountException(50006));
            couponChecker.setCoupon(coupon);
            couponChecker.setUserCoupon(userCoupon);
        }
    }
}
