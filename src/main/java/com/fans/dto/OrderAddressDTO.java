package com.fans.dto;

import lombok.*;

import java.io.Serializable;

/**
 * className: OrderAddressDTO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:13
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderAddressDTO implements Serializable {

    private static final long serialVersionUID = -20200606231312L;

    private String userName;
    private String province;
    private String city;
    private String county;
    private String mobile;
    private String nationalCode;
    private String postalCode;
    private String detail;
}
