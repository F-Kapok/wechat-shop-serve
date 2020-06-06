package com.fans.vo;

import com.fans.entity.Coupon;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: CouponPureVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:51
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CouponPureVO implements Serializable {

    private static final long serialVersionUID = -20200602225133L;

    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate;
    private Integer type;
    private Integer activityId;
    private String remark;
    private Boolean wholeStore;

    public CouponPureVO(Coupon coupon) {
        BeanUtils.copyProperties(coupon, this);
    }

    public static List<CouponPureVO> getList(List<Coupon> couponList) {
        return couponList
                .stream()
                .map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}
