package com.fans.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * className: UpRound
 *
 * @author k
 * @version 1.0
 * @description 向上取整
 * @date 2020-06-07 00:31
 **/
@Component(value = "upRound")
public class UpRound implements IMoneyDiscount {

    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount, Integer scale) {
        return original.multiply(discount).setScale(scale, RoundingMode.UP);
    }

}
