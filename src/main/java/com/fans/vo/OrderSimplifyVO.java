package com.fans.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * className: OrderSimplifyVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 20:20
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderSimplifyVO implements Serializable {

    private static final long serialVersionUID = -20200607202045L;

    private Long id;
    private String orderNo;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    private BigDecimal finalTotalPrice;
    private Integer status;
    private Date expiredTime;
    private Date placedTime;
    private Long period;
    private Date createTime;

}
