package com.fans.vo;

import com.fans.entity.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * className: OrderPureVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 20:39
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderPureVO extends Order implements Serializable {

    private static final long serialVersionUID = -20200607203901L;
    private Long period;
    private Date createTime;

    public OrderPureVO(Order order, Long period) {
        BeanUtils.copyProperties(order, this);
        this.period = period;
    }
}
