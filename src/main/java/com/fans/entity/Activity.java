package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * className: Activity
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-02 22:08
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null and online = 1")
public class Activity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200602220833L;
    @Id
    private Long id;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private String remark;
    private Boolean online;
    private String entranceImg;
    private String internalTopImg;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    private List<Coupon> couponList;


}