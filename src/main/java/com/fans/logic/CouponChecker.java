package com.fans.logic;

import com.fans.core.exception.http.ForbiddenException;
import com.fans.core.exception.http.ParameterException;
import com.fans.core.money.IMoneyDiscount;
import com.fans.entity.Coupon;
import com.fans.entity.UserCoupon;
import com.fans.enums.CouponType;
import com.fans.utils.EnumUtils;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * className: CiyoibChecker
 *
 * @author k
 * @version 1.0
 * @description 优惠券校验
 * @date 2020-06-06 23:26
 **/
@Component(value = "couponChecker")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class CouponChecker {

    private Coupon coupon;
    private UserCoupon userCoupon;


    @Resource(name = "halfEvenRound")
    private IMoneyDiscount iMoneyDiscount;

    public void isOk() {
        Interval interval = new Interval(new DateTime(coupon.getStartTime()), new DateTime(coupon.getEndTime()));
        DateTime now = DateTime.now();
        if (!interval.contains(now)) {
            throw new ForbiddenException(400007);
        }

    }

    public void finalTotalPriceIsOk(BigDecimal orderFinalTotalPrice,
                                    BigDecimal serverTotalPrice) {
        BigDecimal serverFinalTotalPrice = BigDecimal.ZERO;
        switch (EnumUtils.getByCode(coupon.getType(), CouponType.class)) {
            case FULL_MINUS:
            case NO_THRESHOLD_MINUS:
                serverFinalTotalPrice = serverTotalPrice.subtract(coupon.getMinus());
                if (serverFinalTotalPrice.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ForbiddenException(50008);
                }
                break;
            case FULL_OFF:
                serverFinalTotalPrice = iMoneyDiscount.discount(serverTotalPrice, coupon.getRate(), 2);
                break;
            default:
                throw new ParameterException(40009);
        }
        if (serverFinalTotalPrice.compareTo(serverTotalPrice) != 0) {
            throw new ForbiddenException(50008);
        }
    }

    public void canBeUsed() {

    }
}
