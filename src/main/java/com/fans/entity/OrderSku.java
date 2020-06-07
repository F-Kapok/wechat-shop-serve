package com.fans.entity;

import com.fans.dto.SkuInfoDTO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * className: OrderSku
 *
 * @author k
 * @version 1.0
 * @description 插入订单时入参
 * @date 2020-06-07 16:09
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderSku implements Serializable {

    private static final long serialVersionUID = -20200607160934L;

    private Long id;
    private Long spuId;
    /**
     * 购买时的价格
     */
    private BigDecimal finalPrice;
    /**
     * 商品的真实价钱
     */
    private BigDecimal singlePrice;
    private List<String> specValues;
    private Integer count;
    private String img;
    private String title;

    public OrderSku(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.id = sku.getId();
        this.spuId = sku.getSpuId();
        this.singlePrice = sku.getActualPrice();
        this.finalPrice = sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        this.count = skuInfoDTO.getCount();
        this.img = sku.getImg();
        this.title = sku.getTitle();
        this.specValues = sku.getSpecValueList();

    }
}
