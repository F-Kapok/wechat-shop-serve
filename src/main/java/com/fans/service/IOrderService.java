package com.fans.service;

import com.fans.dto.OrderDTO;
import com.fans.entity.Order;
import com.fans.logic.OrderChecker;
import com.fans.manager.redis.model.RedisMessageKey;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * interfaceName: IOrderService
 *
 * @author k
 * @version 1.0
 * @description 订单服务层
 * @date 2020-06-06 23:04
 **/
public interface IOrderService {

    OrderChecker isOk(Long userId, OrderDTO orderDTO);

    Long placeOrder(Long userId, OrderDTO orderDTO, OrderChecker orderChecker);

    Page<Order> getUnpaid(int pageNum, Integer count);

    Page<Order> getByStatus(int status, int pageNum, Integer count);

    Optional<Order> getOrderDetail(Long orderId);

    Order updateOrderPrepayId(Long orderId, String prePayId);

    void sendToRedis(Long orderId, Long userId, Long couponId);

    /**
     * description: 订单定时取消 并且库存归还
     *
     * @param redisMessageKey redis通知Key
     * @author k
     * @date 2020/07/20 21:02
     **/
    void cancelOrder(RedisMessageKey redisMessageKey);

    /**
     * description: 订单定时取消 并且归还优惠券
     *
     * @param redisMessageKey redis通知Key
     * @author k
     * @date 2020/07/20 21:04
     **/
    void returnBackCoupon(RedisMessageKey redisMessageKey);
}
