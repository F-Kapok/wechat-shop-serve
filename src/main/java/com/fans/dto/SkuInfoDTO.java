package com.fans.dto;

import lombok.*;

import java.io.Serializable;

/**
 * className: SkuInfoDTO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:12
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SkuInfoDTO implements Serializable {

    private static final long serialVersionUID = -20200606231238L;

    private Long id;

    private Integer count;

}
