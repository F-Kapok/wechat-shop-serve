package com.fans.bo;

import com.fans.dto.SkuInfoDTO;
import com.fans.entity.Sku;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * className: SkuOrderBO
 *
 * @author k
 * @version 1.0
 * @description 优惠券校验是否能够使用传参
 * @date 2020-06-07 11:07
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SkuOrderBO implements Serializable {

    private static final long serialVersionUID = -20200607110707L;

    private BigDecimal actualPrice;

    private Integer count;

    private Long categoryId;


    public SkuOrderBO(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice = sku.getActualPrice();
        this.count = skuInfoDTO.getCount();
        this.categoryId = sku.getCategoryId();
    }

    public BigDecimal getTotalPrice() {
        return actualPrice.multiply(new BigDecimal(count));
    }
}
