package com.fans.service.impl;

import com.fans.entity.Banner;
import com.fans.repository.BannerRepository;
import com.fans.service.IBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * className: BannerServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 13:56
 **/
@Service(value = "iBannerService")
public class BannerServiceImpl implements IBannerService {

    @Resource(type = BannerRepository.class)
    private BannerRepository bannerRepository;

    @Override
    public Banner getBannerByName(String name) {
        return bannerRepository.findByName(name);
    }
}
