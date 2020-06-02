package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * className: Category
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:38
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null and online = 1")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200531153842L;

    @Id
    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private Long parentId;
    private String img;
    private Integer index;
    private Integer online;
    private Integer level;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coupon_category", joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "coupon_id")})
    List<Coupon> couponList;

}