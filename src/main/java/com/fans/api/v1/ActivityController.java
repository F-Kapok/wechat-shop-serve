package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.core.exception.http.NotFountException;
import com.fans.entity.Activity;
import com.fans.service.IActivityService;
import com.fans.vo.ActivityCouponVO;
import com.fans.vo.ActivityPureVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * className: ActivityController
 *
 * @author k
 * @version 1.0
 * @description 活动控制层
 * @date 2020-06-02 21:46
 **/
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Resource(name = "iActivityService")
    private IActivityService iActivityService;

    @GetMapping(value = "/name/{name}")
    @ScopeLevel
    public ActivityPureVO getHomeActivity(@PathVariable String name) {
        Optional<Activity> activityOptional = iActivityService.getByName(name);
        if (!activityOptional.isPresent()) {
            throw new NotFountException(40001);
        }
        return new ActivityPureVO(activityOptional.get());
    }


    @GetMapping(value = "/name/{name}/with_coupon")
    @ScopeLevel
    public ActivityCouponVO getActivityWithCoupon(@PathVariable String name) {
        Optional<Activity> activityOptional = iActivityService.getByName(name);
        if (!activityOptional.isPresent()) {
            throw new NotFountException(40001);
        }
        return new ActivityCouponVO(activityOptional.get());
    }
}
