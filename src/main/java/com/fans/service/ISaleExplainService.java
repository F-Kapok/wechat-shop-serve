package com.fans.service;

import com.fans.entity.SaleExplain;

import java.util.List;

/**
 * interfaceName: ISaleExplainService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 16:32
 **/
public interface ISaleExplainService {

    List<SaleExplain> getSaleExplainBySpuId(Long spuId);
}
