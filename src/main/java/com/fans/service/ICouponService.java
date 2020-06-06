package com.fans.service;

import com.fans.entity.Coupon;

import java.util.List;

/**
 * interfaceName: ICounponService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 00:22
 **/
public interface ICouponService {

    List<Coupon> getByCategory(Long cid);

    List<Coupon> getWholeStoreCoupons();

    void collectOneCoupon(Long couponId, Long uid);

    List<Coupon> getMyAvailableCoupons(Long uid);

    List<Coupon> getMyUsedCoupons(Long uid);

    List<Coupon> getMyExpiredCoupons(Long uid);
}
