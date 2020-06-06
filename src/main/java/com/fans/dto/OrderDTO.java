package com.fans.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * className: OrderDTO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:10
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -20200606231056L;

    private BigDecimal totalPrice;

    private BigDecimal finalTotalPrice;

    private Long couponId;

    private List<SkuInfoDTO> skuInfoList;

    private OrderAddressDTO address;


}
