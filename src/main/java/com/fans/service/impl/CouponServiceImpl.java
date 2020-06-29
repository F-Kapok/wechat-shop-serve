package com.fans.service.impl;

import com.fans.common.LocalUser;
import com.fans.core.exception.http.NotFountException;
import com.fans.core.exception.http.ParameterException;
import com.fans.entity.Activity;
import com.fans.entity.Coupon;
import com.fans.entity.UserCoupon;
import com.fans.enums.CouponStatus;
import com.fans.repository.ActivityRepository;
import com.fans.repository.CouponRepository;
import com.fans.repository.UserCouponRepository;
import com.fans.service.ICouponService;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.fans.common.CommonUtils.setCouponStatus;

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
    @Resource(type = ActivityRepository.class)
    private ActivityRepository activityRepository;
    @Resource(type = UserCouponRepository.class)
    private UserCouponRepository userCouponRepository;

    @Override
    public List<Coupon> getByCategory(Long cid) {
        List<Coupon> couponList = couponRepository.findByCategoryId(cid, DateTime.now().toDate());
        setCouponStatus(couponList, userCouponRepository);
        return couponList;
    }


    @Override
    public List<Coupon> getWholeStoreCoupons() {
        List<Coupon> couponList = couponRepository.getWholeStoreCoupons(true, DateTime.now().toDate());
        setCouponStatus(couponList, userCouponRepository);
        return couponList;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void collectOneCoupon(Long couponId, Long uid) {
        couponRepository
                .findById(couponId)
                .orElseThrow(() -> new NotFountException(40003));

        Activity activity = activityRepository.findByCouponListId(couponId).orElseThrow(() -> new NotFountException(40010));
        DateTime now = DateTime.now();
        Interval interval = new Interval(new DateTime(activity.getStartTime()), new DateTime(activity.getEndTime()));
        if (!interval.contains(now)) {
            throw new ParameterException(40005);
        }
        userCouponRepository.findByCouponIdAndUserId(couponId, uid).ifPresent(userCoupon -> {
            throw new ParameterException(40006);
        });
        UserCoupon userCoupon = UserCoupon.builder()
                .couponId(couponId)
                .userId(uid)
                .status(CouponStatus.AVAILABLE.getCode())
                .createTime(now.toDate())
                .updateTime(now.toDate())
                .build();
        userCouponRepository.save(userCoupon);
    }

    @Override
    public List<Coupon> getMyAvailableCoupons(Long uid) {
        DateTime now = DateTime.now();
        return couponRepository.findMyAvailableCoupons(uid, now.toDate());
    }

    @Override
    public List<Coupon> getMyUsedCoupons(Long uid) {
        DateTime now = DateTime.now();
        return couponRepository.findMyUsedCoupons(uid, now.toDate());
    }

    @Override
    public List<Coupon> getMyExpiredCoupons(Long uid) {
        DateTime now = DateTime.now();
        return couponRepository.findMyExpiredCoupons(uid, now.toDate());
    }
}
