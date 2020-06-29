package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.common.LocalUser;
import com.fans.core.exception.http.CreateSuccess;
import com.fans.core.exception.http.ParameterException;
import com.fans.entity.Coupon;
import com.fans.enums.CouponStatus;
import com.fans.service.ICouponService;
import com.fans.utils.EnumUtils;
import com.fans.vo.CouponCategoryVO;
import com.fans.vo.CouponPureVO;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @ScopeLevel
    public List<CouponPureVO> getCouponListByCategory(@PathVariable Long cid) {
        List<Coupon> coupons = iCouponService.getByCategory(cid);
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }
        return CouponPureVO.getList(coupons);
    }

    @GetMapping("/whole_store")
    @ScopeLevel
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<Coupon> coupons = iCouponService.getWholeStoreCoupons();
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }
        return CouponPureVO.getList(coupons);
    }


    @PostMapping(value = "/collect/{id}")
    @ScopeLevel
    public void collectCoupon(@PathVariable(name = "id") Long couponId) {
        Long uid = LocalUser.getUser().getId();
        iCouponService.collectOneCoupon(couponId, uid);
        throw new CreateSuccess(0);
    }


    @GetMapping(value = "/myself/by/status/{status}")
    @ScopeLevel
    public List<CouponPureVO> getMyCouponByStatus(@PathVariable Integer status) {
        Long userId = LocalUser.getUser().getId();
        List<Coupon> couponList;
        switch (EnumUtils.getByCode(status, CouponStatus.class)) {
            case AVAILABLE:
                couponList = iCouponService.getMyAvailableCoupons(userId);
                break;
            case USED:
                couponList = iCouponService.getMyUsedCoupons(userId);
                break;
            case EXPIRED:
                couponList = iCouponService.getMyExpiredCoupons(userId);
                break;
            default:
                throw new ParameterException(40001);
        }
        return CouponPureVO.getList(couponList);
    }

    @GetMapping("/myself/available/with_category")
    @ScopeLevel
    public List<CouponCategoryVO> getUserCouponWithCategory() {
        Long userId = LocalUser.getUser().getId();
        List<Coupon> couponList = iCouponService.getMyAvailableCoupons(userId);
        if (couponList.isEmpty()) {
            return Lists.newArrayList();
        }
        return couponList.stream().map(CouponCategoryVO::new).collect(Collectors.toList());
    }
}
