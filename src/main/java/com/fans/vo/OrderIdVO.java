package com.fans.vo;

import lombok.*;

import java.io.Serializable;

/**
 * className: OrderIdVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 19:09
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderIdVO implements Serializable {

    private static final long serialVersionUID = -20200607190919L;
    private Long id;

}
