package com.fans.service.impl;

import com.fans.entity.Activity;
import com.fans.entity.Coupon;
import com.fans.repository.ActivityRepository;
import com.fans.repository.UserCouponRepository;
import com.fans.service.IActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.fans.common.CommonUtils.setCouponStatus;

/**
 * className: ActivityServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:42
 **/
@Service(value = "iActivityService")
public class ActivityServiceImpl implements IActivityService {

    @Resource(type = ActivityRepository.class)
    private ActivityRepository activityRepository;
    @Resource(type = UserCouponRepository.class)
    private UserCouponRepository userCouponRepository;

    @Override
    public Optional<Activity> getByName(String name) {
        Optional<Activity> activityOptional = activityRepository.findByName(name);
        activityOptional.ifPresent(activity -> {
            activity.setCouponList(setCouponStatus(activity.getCouponList(), userCouponRepository));
        });
        return activityOptional;
    }
}
