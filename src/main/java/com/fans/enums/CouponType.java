package com.fans.enums;

import com.fans.common.CodeEnum;

/**
 * enumName: CouponType
 *
 * @author k
 * @version 1.0
 * @description 优惠券
 * @date 2020-06-07 00:14
 **/
public enum CouponType implements CodeEnum {
    /**
     *
     */
    FULL_MINUS(1, "满减券"),
    FULL_OFF(2, "满减折扣券"),
    NO_THRESHOLD_MINUS(3, "无门槛减除券");

    private final Integer code;
    private final String desc;

    CouponType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
