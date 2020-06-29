package com.fans.repository;

import com.fans.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * interfaceName: ActivityRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:44
 **/
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findByName(String name);

    Optional<Activity> findByCouponListId(Long couponId);

}
