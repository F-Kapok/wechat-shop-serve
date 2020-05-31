package com.fans.vo;

import lombok.*;

import java.io.Serializable;

/**
 * className: SpuSimplifyVO
 *
 * @author k
 * @version 1.0
 * @description spu简化视图
 * @date 2020-05-30 20:03
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SpuSimplifyVO implements Serializable {

    private static final long serialVersionUID = -20200530200331L;

    private Long id;
    private String title;
    private String subtitle;
    private String img;
    private String forThemeImg;
    private String price;
    private String discountPrice;
    private String description;
    private String tags;
    private String sketchSpecId;


}
