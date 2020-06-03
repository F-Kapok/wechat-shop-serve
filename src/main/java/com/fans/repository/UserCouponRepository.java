package com.fans.repository;

import com.fans.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
