package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * className: Coupon
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-01 23:54
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null")
public class Coupon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200601235448L;

    @Id
    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate;
    private Integer type;
    @Column(name = "activity_id")
    private Long activityId;
    private String remark;
    private Boolean wholeStore;
    @Transient
    private Integer status;
    @ManyToMany(mappedBy = "couponList", fetch = FetchType.LAZY)
    List<Category> categoryList;

}