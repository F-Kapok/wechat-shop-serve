package com.fans.manager.redis.model;

import lombok.*;

import java.io.Serializable;

/**
 * className: RedisMessageKey
 *
 * @author k
 * @version 1.0
 * @description redis消息返回key模型
 * @date 2020-07-19 18:00
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RedisMessageKey implements Serializable {

    private static final long serialVersionUID = -20200719180048L;

    private Long orderId;
    private Long userId;
    private Long couponId;

}
