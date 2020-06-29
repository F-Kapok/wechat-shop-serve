package com.fans.api.v1;

import com.fans.entity.SaleExplain;
import com.fans.service.ISaleExplainService;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: SaleExplainController
 *
 * @author k
 * @version 1.0
 * @description spu追加解释信息控制层
 * @date 2020-06-29 16:28
 **/
@RestController
@RequestMapping(value = "/sale_explain")
public class SaleExplainController {

    @Resource(name = "iSaleExplainService")
    private ISaleExplainService iSaleExplainService;

    @GetMapping(value = "/fixed")
    private List<SaleExplain> getSaleExplainBySpu(@RequestParam(required = false, defaultValue = "0") Long spuId) {
        List<SaleExplain> saleExplainList = iSaleExplainService.getSaleExplainBySpuId(spuId);
        return saleExplainList.isEmpty() ? Lists.newArrayList() : saleExplainList;
    }
}
