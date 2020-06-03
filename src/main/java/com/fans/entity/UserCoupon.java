package com.fans.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * className: UserCoupon
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-03 22:46
 **/
@Entity
@Table(name = "user_coupon", schema = "wechat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = -20200603224654L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long couponId;
    private Long orderId;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}