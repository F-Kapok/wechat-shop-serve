package com.fans.vo;

import com.fans.entity.Activity;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: ActivityCouponVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:49
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ActivityCouponVO extends ActivityPureVO implements Serializable {

    private static final long serialVersionUID = -20200602224920L;

    private List<CouponPureVO> coupons;

    public ActivityCouponVO(Activity activity) {
        super(activity);
        coupons = activity.getCouponList()
                .stream()
                .map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}
