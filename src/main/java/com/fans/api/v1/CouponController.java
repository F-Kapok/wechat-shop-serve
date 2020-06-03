package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.common.LocalUser;
import com.fans.core.exception.http.CreateSuccess;
import com.fans.entity.Coupon;
import com.fans.service.ICouponService;
import com.fans.vo.CouponPureVO;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: CouponController
 *
 * @author k
 * @version 1.0
 * @description 优惠券控制层
 * @date 2020-06-03 00:19
 **/
@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

    @Resource(name = "iCouponService")
    private ICouponService iCouponService;


    @GetMapping(value = "/by/category/{cid}")
    public List<CouponPureVO> getCouponListByCategory(@PathVariable Long cid) {
        List<Coupon> coupons = iCouponService.getByCategory(cid);
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }
        return CouponPureVO.getList(coupons);
    }

    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<Coupon> coupons = iCouponService.getWholeStoreCoupons();
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }
        return CouponPureVO.getList(coupons);
    }

    @ScopeLevel
    @PostMapping(value = "/collect/{id}")
    public void collectCoupon(@PathVariable(name = "id") Long couponId) {
        Long uid = LocalUser.getUser().getId();
        iCouponService.collectOneCoupon(couponId, uid);
        throw new CreateSuccess(0);
    }
}
