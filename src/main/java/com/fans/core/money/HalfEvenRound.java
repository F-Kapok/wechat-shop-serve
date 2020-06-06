package com.fans.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * className: HalfEvenRound
 *
 * @author k
 * @version 1.0
 * @description 银行家算法
 * @date 2020-06-07 00:30
 **/
@Component(value = "halfEvenRound")
public class HalfEvenRound implements IMoneyDiscount {

    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount, Integer scale) {
        return original.multiply(discount).setScale(scale, RoundingMode.HALF_EVEN);
    }

}
