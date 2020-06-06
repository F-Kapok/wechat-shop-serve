package com.fans.service.impl;

import com.fans.entity.Sku;
import com.fans.repository.SkuRepository;
import com.fans.service.ISkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: SkuServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:17
 **/
@Service(value = "iSkuService")
public class SkuServiceImpl implements ISkuService {

    @Resource(type = SkuRepository.class)
    private SkuRepository skuRepository;

    @Override
    public List<Sku> getSkuListByIds(List<Long> skuIds) {
        return skuRepository.findByIdIn(skuIds);
    }
}
