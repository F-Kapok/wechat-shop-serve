package com.fans.logic;

import com.fans.bo.SkuOrderBO;
import com.fans.core.exception.http.ForbiddenException;
import com.fans.core.exception.http.ParameterException;
import com.fans.core.money.IMoneyDiscount;
import com.fans.entity.Category;
import com.fans.entity.Coupon;
import com.fans.enums.CouponType;
import com.fans.utils.EnumUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: CiyoibChecker
 *
 * @author k
 * @version 1.0
 * @description 优惠券校验
 * @date 2020-06-06 23:26
 **/
//@Component(value = "couponChecker")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CouponChecker {

    private final Coupon coupon;

    public CouponChecker(Coupon coupon) {
        this.coupon = coupon;
    }

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
        BigDecimal serverFinalTotalPrice;
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
        if (serverFinalTotalPrice.compareTo(orderFinalTotalPrice) != 0) {
            throw new ForbiddenException(50008);
        }
    }

    public void canBeUsed(List<SkuOrderBO> skuOrderBOList, BigDecimal serverTotalPrice) {
        BigDecimal orderCategoryPrice;
        if (coupon.getWholeStore()) {
            orderCategoryPrice = serverTotalPrice;
        } else {
            List<Long> categoryIdList = coupon.getCategoryList().stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());
            orderCategoryPrice = getSumByCategoryList(skuOrderBOList, categoryIdList);
        }
        couponCanBeUsed(orderCategoryPrice);
    }

    private void couponCanBeUsed(BigDecimal orderCategoryPrice) {
        switch (EnumUtils.getByCode(coupon.getType(), CouponType.class)) {
            case FULL_MINUS:
            case FULL_OFF:
                int compare = coupon.getFullMoney().compareTo(orderCategoryPrice);
                if (compare > 0) {
                    throw new ParameterException(40008);
                }
                break;
            case NO_THRESHOLD_MINUS:
                break;
            default:
                throw new ParameterException(40009);
        }
    }

    private BigDecimal getSumByCategoryList(List<SkuOrderBO> skuOrderBOList, List<Long> categoryIds) {
        return categoryIds.stream().map(categoryId -> getSumByCategory(skuOrderBOList, categoryId))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    private BigDecimal getSumByCategory(List<SkuOrderBO> skuOrderBOList, Long categoryId) {
        return skuOrderBOList.stream()
                .filter(skuOrderBO -> skuOrderBO.getCategoryId().equals(categoryId))
                .map(SkuOrderBO::getTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
