package com.fans.enums;

import com.fans.common.CodeEnum;

/**
 * enumName: CouponStatus
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 23:32
 **/
public enum CouponStatus implements CodeEnum {
    /**
     * 1:未使用，2：已使用， 3：已过期
     */
    AVAILABLE(1, "可以使用,未过期"),
    USED(2, "已使用"),
    EXPIRED(3, "未使用，已过期");

    private final Integer code;
    private final String desc;

    CouponStatus(Integer code, String desc) {
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
