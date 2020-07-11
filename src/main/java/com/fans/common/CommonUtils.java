package com.fans.common;

import com.fans.entity.Coupon;
import com.fans.entity.UserCoupon;
import com.fans.enums.CouponStatus;
import com.fans.repository.UserCouponRepository;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * className: CommonUtils
 *
 * @author k
 * @version 1.0
 * @description 通用service方法
 * @date 2020-06-29 14:15
 **/
public class CommonUtils {

    public static void setCouponStatus(List<Coupon> couponList, UserCouponRepository userCouponRepository) {
        couponList.forEach(coupon -> {
            Optional<UserCoupon> userCouponOptional = userCouponRepository.findByCouponIdAndUserId(coupon.getId(), LocalUser.getUser().getId());
            userCouponOptional.ifPresent(userCoupon -> {
                Interval interval = new Interval(new DateTime(coupon.getStartTime().getTime()), new DateTime(coupon.getEndTime().getTime()));
                if (userCoupon.getStatus().equals(CouponStatus.AVAILABLE.getCode())
                        && interval.contains(DateTime.now())
                        && userCoupon.getOrderId() == null) {
                    coupon.setStatus(CouponStatus.AVAILABLE.getCode());
                } else if (userCoupon.getStatus().equals(CouponStatus.USED.getCode())) {
                    coupon.setStatus(CouponStatus.USED.getCode());
                } else if(!interval.contains(DateTime.now())&&userCoupon.getOrderId() ==null){
                    coupon.setStatus(CouponStatus.EXPIRED.getCode());
                }else{
                    coupon.setStatus(0);
                }
            });
        });
    }

    public static String yuan2FenPlainString(BigDecimal price) {
        return price.multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
    }
}
