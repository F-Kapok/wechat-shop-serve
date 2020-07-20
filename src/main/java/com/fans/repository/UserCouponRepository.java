package com.fans.repository;

import com.fans.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * interfaceName: UserCouponRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 23:28
 **/
@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    Optional<UserCoupon> findByCouponIdAndUserId(Long couponId, Long userId);

    Optional<UserCoupon> findFirstByUserIdAndCouponIdAndStatusAndOrderIdIsNull(Long userId, Long couponId, Integer status);

    @Modifying
    @Query(value = "update UserCoupon uc \n" +
            "set uc.status = 2,\n" +
            "uc.orderId = :orderId\n" +
            "where uc.userId = :userId\n" +
            "and uc.couponId = :couponId\n" +
            "and uc.status = 1\n" +
            "and uc.orderId is null")
    int writeOff(@Param("couponId") Long couponId, @Param("orderId") Long orderId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "update UserCoupon uc \n" +
            "set uc.status = 1, uc.orderId = null \n" +
            "where uc.couponId = :couponId \n" +
            "and uc.userId = :userId \n" +
            "and uc.orderId is not null \n" +
            "and uc.status = 2" +
            " ")
    int recoverCoupon(@Param("couponId") Long couponId, @Param("userId") Long userId);
}
