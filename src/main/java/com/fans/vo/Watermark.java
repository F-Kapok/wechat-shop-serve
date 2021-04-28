package com.fans.vo;

import lombok.*;

import java.io.Serializable;

/**
 * className: Watermark
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2021-04-23 11:57
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Watermark implements Serializable {

    private static final long serialVersionUID = -20210423115740L;

    private Long timestamp;
    private String appid;
}
