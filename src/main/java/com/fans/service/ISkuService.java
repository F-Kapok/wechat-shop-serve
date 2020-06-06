package com.fans.service;

import com.fans.entity.Sku;

import java.util.List;

/**
 * interfaceName: ISkuService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:16
 **/
public interface ISkuService {

    List<Sku> getSkuListByIds(List<Long> skuIds);
}
