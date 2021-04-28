package com.fans.vo;

import lombok.*;

import java.io.Serializable;

/**
 * className: MobileVO
 *
 * @author k
 * @version 1.0
 * @description 手机号视图
 * @date 2021-04-23 11:55
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MobileVO implements Serializable {

    private static final long serialVersionUID = -20210423115515L;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private Watermark watermark;

}
