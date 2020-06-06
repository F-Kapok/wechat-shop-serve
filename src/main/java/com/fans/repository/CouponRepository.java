package com.fans.repository;

import com.fans.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * interfaceName: CouponRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 00:24
 **/
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {


    //    @Query(nativeQuery = true, value = "select * from coupon cou \n" +
//            "left join coupon_category cc on cc.coupon_id = cou.id\n" +
//            "left join  category c on c.id = cc.category_id\n" +
//            "left join activity a on a.id = cou.activity_id\n" +
//            "where c.id = :cid\n" +
//            "and a.start_time < :now\n" +
//            "and a.end_time > :now")
    @Query(value = "select c from Coupon c\n" +
            "left join c.categoryList cc\n" +
            "left join Activity a on a.id = c.activityId\n" +
            "where cc.id = :cid\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now\n")
    List<Coupon> findByCategoryId(Long cid, Date now);

    @Query(value = "select c from Coupon c\n" +
            "left join Activity a on a.id = c.activityId\n" +
            "where c.wholeStore = :isWholeStore\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now")
    List<Coupon> getWholeStoreCoupons(boolean isWholeStore, Date now);

    @Query(value = "select c from Coupon c \n" +
            "left join UserCoupon uc on uc.couponId = c.id\n" +
            "left join   User u on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 1\n" +
            "and c.startTime <= :now\n" +
            "and c.endTime >= :now\n" +
            "and uc.orderId is null")
    List<Coupon> findMyAvailableCoupons(Long uid, Date now);


    @Query(value = "select c from Coupon c\n" +
            "left join UserCoupon uc on uc.couponId = c.id\n" +
            "left join User  u on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 2\n" +
            "and c.startTime <= :now\n" +
            "and c.endTime >= :now\n" +
            "and uc.orderId is not null")
    List<Coupon> findMyUsedCoupons(Long uid, Date now);

    @Query(value = "select c from Coupon c\n" +
            "left join   UserCoupon uc on uc.couponId = c.id\n" +
            "left join User u on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status <> 2\n" +
            "and c.endTime < :now\n" +
            "and uc.orderId is null")
    List<Coupon> findMyExpiredCoupons(Long uid, Date now);
}
