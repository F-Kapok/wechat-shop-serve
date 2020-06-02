package com.fans.service.impl;

import com.fans.entity.Coupon;
import com.fans.repository.CouponRepository;
import com.fans.service.ICouponService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: CouponServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 00:23
 **/
@Service(value = "iCouponService")
public class CouponServiceImpl implements ICouponService {
    @Resource(type = CouponRepository.class)
    private CouponRepository couponRepository;

    @Override
    public List<Coupon> getByCategory(Long cid) {
        return couponRepository.findByCategoryId(cid, DateTime.now().toDate());
    }

    @Override
    public List<Coupon> getWholeStoreCoupons() {

        return couponRepository.getWholeStoreCoupons(true, DateTime.now().toDate());
    }
}
