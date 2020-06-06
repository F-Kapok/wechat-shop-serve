package com.fans.core.money;

import java.math.BigDecimal;

/**
 * interfaceName: IMoneyDiscount
 *
 * @author k
 * @version 1.0
 * @description 价格相关计算
 * @date 2020-06-07 00:26
 **/
public interface IMoneyDiscount {
    /**
     * description: 折扣率计算（乘法）
     *
     * @param original 数据来源
     * @param discount 折扣率
     * @param scale    保留几位小数
     * @return java.math.BigDecimal
     * @author k
     * @date 2020/06/07 0:28
     **/
    BigDecimal discount(BigDecimal original, BigDecimal discount, Integer scale);
}
