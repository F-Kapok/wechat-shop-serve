package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.common.LocalUser;
import com.fans.core.exception.http.NotFountException;
import com.fans.dto.OrderDTO;
import com.fans.entity.Order;
import com.fans.logic.OrderChecker;
import com.fans.service.IOrderService;
import com.fans.vo.OrderIdVO;
import com.fans.vo.OrderPureVO;
import com.fans.vo.OrderSimplifyVO;
import com.fans.vo.PagingDozer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * className: OrderController
 *
 * @author k
 * @version 1.0
 * @description 订单控制层
 * @date 2020-06-07 16:35
 **/
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Resource(name = "iOrderService")
    private IOrderService iOrderService;
    @Value("${kapok.order.pay-time-limit}")
    private Long payTimeLimit;

    @PostMapping(value = "")
    @ScopeLevel
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long userId = LocalUser.getUser().getId();
        OrderChecker orderChecker = iOrderService.isOk(userId, orderDTO);
        Long orderId = iOrderService.placeOrder(userId, orderDTO, orderChecker);
        return new OrderIdVO(orderId);
    }

    @ScopeLevel
    @GetMapping("/detail/{id}")
    public OrderPureVO getOrderDetail(@PathVariable(name = "id") Long oid) {
        Optional<Order> orderOptional = iOrderService.getOrderDetail(oid);
        return orderOptional.map((o) -> new OrderPureVO(o, payTimeLimit))
                .orElseThrow(() -> new NotFountException(50009));
    }

    @ScopeLevel
    @GetMapping("/status/unpaid")
    @SuppressWarnings("unchecked")
    public PagingDozer<Order, OrderSimplifyVO> getUnpaid(@RequestParam(defaultValue = "0")
                                                                 Integer start,
                                                         @RequestParam(defaultValue = "10")
                                                                 Integer count) {
        Page<Order> paging = iOrderService.getUnpaid(start / count, count);
        PagingDozer<Order, OrderSimplifyVO> dozer = new PagingDozer<>(paging, OrderSimplifyVO.class);
        dozer.getItems().forEach(o -> ((OrderSimplifyVO) o).setPeriod(payTimeLimit));
        return dozer;
    }

    @ScopeLevel
    @GetMapping("/by/status/{status}")
    @SuppressWarnings("unchecked")
    public PagingDozer<Order, OrderSimplifyVO> getByStatus(@PathVariable int status,
                                                           @RequestParam(name = "start", defaultValue = "0")
                                                                   Integer start,
                                                           @RequestParam(name = "count", defaultValue = "10")
                                                                   Integer count) {
        Page<Order> paging = iOrderService.getByStatus(status, start / count, count);
        PagingDozer<Order, OrderSimplifyVO> dozer = new PagingDozer<>(paging, OrderSimplifyVO.class);
        dozer.getItems().forEach(o -> ((OrderSimplifyVO) o).setPeriod(payTimeLimit));
        return dozer;
    }
}
