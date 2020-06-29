package com.fans.service.impl;

import com.fans.entity.SaleExplain;
import com.fans.repository.SaleExplainRepository;
import com.fans.service.ISaleExplainService;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * className: SaleExplainServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 16:33
 **/
@Service(value = "iSaleExplainService")
public class SaleExplainServiceImpl implements ISaleExplainService {

    @Resource(type = SaleExplainRepository.class)
    private SaleExplainRepository saleExplainRepository;

    @Override
    public List<SaleExplain> getSaleExplainBySpuId(Long spuId) {
        return saleExplainRepository.findBySpuId(spuId);
    }
}
