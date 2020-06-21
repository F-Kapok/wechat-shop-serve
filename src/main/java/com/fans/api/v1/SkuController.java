package com.fans.api.v1;

import com.fans.entity.Sku;
import com.fans.service.ISkuService;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * className: SkuController
 *
 * @author k
 * @version 1.0
 * @description 商品规则控制层
 * @date 2020-06-21 21:42
 **/
@RestController
@RequestMapping(value = "/sku")
public class SkuController implements Serializable {


    @Resource(name = "iSkuService")
    private ISkuService iSkuService;


    @GetMapping
    public List<Sku> getSkuListByIds(@RequestParam(name = "ids", required = false) String ids) {
        if (StringUtils.isBlank(ids)) {
            return Lists.newArrayList();
        }
        return iSkuService.getSkuListByIds(Splitter.on(",").trimResults().omitEmptyStrings().splitToList(ids).stream().map(Long::valueOf).collect(Collectors.toList()));
    }

}
