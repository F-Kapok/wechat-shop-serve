package com.fans.api.v1;

import com.fans.entity.Coupon;
import com.fans.service.ICouponService;
import com.fans.vo.CouponPureVO;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
