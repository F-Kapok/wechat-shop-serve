package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * className: Spu
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 16:30
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null and online = 1")
public class Spu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200530163054L;

    @Id
    private Long id;
    private String title;
    private String subtitle;
    private Long categoryId;
    private Long rootCategoryId;
    private Boolean online;
    private String price;
    private Long sketchSpecId;
    private Long defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private Boolean isTest;
    private String forThemeImg;
//    private String spuThemeImg;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spu_id")
    private List<SpuImg> spuImgList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spu_id")
    private List<SpuDetailImg> spuDetailImgList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spu_id")
    private List<Sku> skuList;
}
