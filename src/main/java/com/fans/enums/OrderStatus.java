package com.fans.enums;

import java.util.Arrays;

/**
 * enumName: OrderStatus
 *
 * @author k
 * @version 1.0
 * @description 订单状态
 * @date 2020-06-07 18:15
 **/
public enum OrderStatus {
    /**
     *
     */
    All(0, "全部"),
    UNPAID(1, "待支付"),
    PAID(2, "已支付"),
    DELIVERED(3, "已发货"),
    FINISHED(4, "已完成"),
    CANCELED(5, "已取消"),
    // 预扣除库存不存在以下这两种情况
    PAID_BUT_OUT_OF(21, "已支付，但无货或库存不足"),
    DEAL_OUT_OF(22, "已处理缺货但支付的情况"),
    OTHER(-1, "其他");

    private final Integer code;
    private final String desc;

    OrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatus get(Integer code) {
        return Arrays.stream(OrderStatus.values())
                .filter(value -> value.getCode().equals(code))
                .findAny()
                .orElse(OrderStatus.OTHER);
    }

    public static OrderStatus get(String desc) {
        return Arrays.stream(OrderStatus.values())
                .filter(value -> value.getDesc().equals(desc))
                .findAny()
                .orElse(OrderStatus.OTHER);
    }

}
