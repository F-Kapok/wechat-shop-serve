package com.fans.service;

import com.fans.entity.Activity;

import java.util.Optional;

/**
 * interfaceName: IActivityService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:41
 **/
public interface IActivityService {

    Optional<Activity> getByName(String name);

}
