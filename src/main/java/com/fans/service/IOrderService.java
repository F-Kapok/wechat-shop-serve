package com.fans.service;

import com.fans.dto.OrderDTO;

/**
 * interfaceName: IOrderService
 *
 * @author k
 * @version 1.0
 * @description 订单服务层
 * @date 2020-06-06 23:04
 **/
public interface IOrderService {

    void isOk(Long userId, OrderDTO orderDTO);
}
