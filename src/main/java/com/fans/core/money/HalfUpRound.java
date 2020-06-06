package com.fans.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * className: HalfUpRound
 *
 * @author k
 * @version 1.0
 * @description 四舍五入计算
 * @date 2020-06-07 00:27
 **/
@Component(value = "halfUpRound")
public class HalfUpRound implements IMoneyDiscount {


    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount, Integer scale) {
        return original.multiply(discount).setScale(scale, RoundingMode.HALF_UP);
    }
}
