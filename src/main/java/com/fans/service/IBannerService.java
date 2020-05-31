package com.fans.service;

import com.fans.entity.Banner;

/**
 * interfaceName: IBannerService
 *
 * @author k
 * @version 1.0
 * @description banner服务层
 * @date 2020-05-30 13:56
 **/
public interface IBannerService {

    Banner getBannerByName(String name);
}
