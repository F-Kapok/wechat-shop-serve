package com.fans.service;

import com.fans.dto.OrderDTO;
import com.fans.entity.Order;
import com.fans.logic.OrderChecker;
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
}
