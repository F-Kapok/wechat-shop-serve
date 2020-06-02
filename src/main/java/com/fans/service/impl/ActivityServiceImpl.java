package com.fans.service.impl;

import com.fans.entity.Activity;
import com.fans.repository.ActivityRepository;
import com.fans.service.IActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

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

    @Override
    public Optional<Activity> getByName(String name) {
        return activityRepository.findByName(name);
    }
}
