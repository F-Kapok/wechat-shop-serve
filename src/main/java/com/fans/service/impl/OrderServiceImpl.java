package com.fans.service.impl;

import com.fans.common.LocalUser;
import com.fans.core.exception.http.ForbiddenException;
import com.fans.core.exception.http.NotFountException;
import com.fans.core.exception.http.ParameterException;
import com.fans.dto.OrderDTO;
import com.fans.dto.SkuInfoDTO;
import com.fans.entity.Coupon;
import com.fans.entity.Order;
import com.fans.entity.OrderSku;
import com.fans.entity.Sku;
import com.fans.enums.CouponStatus;
import com.fans.enums.OrderStatus;
import com.fans.logic.CouponChecker;
import com.fans.logic.OrderChecker;
import com.fans.repository.CouponRepository;
import com.fans.repository.OrderRepository;
import com.fans.repository.SkuRepository;
import com.fans.repository.UserCouponRepository;
import com.fans.service.IOrderService;
import com.fans.service.ISkuService;
import com.fans.utils.OrderUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * className: OrderServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:05
 **/
@Service(value = "iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Resource(name = "iSkuService")
    private ISkuService iSkuService;
    @Resource(type = CouponRepository.class)
    private CouponRepository couponRepository;
    @Resource(type = UserCouponRepository.class)
    private UserCouponRepository userCouponRepository;
    @Resource(type = OrderRepository.class)
    private OrderRepository orderRepository;
    @Resource(type = SkuRepository.class)
    private SkuRepository skuRepository;
    @Value(value = "${kapok.order.max-sku-limit}")
    private Integer maxSkuLimit;
    @Value("${kapok.order.pay-time-limit}")
    private Integer payTimeLimit;

    @Override
    public OrderChecker isOk(Long userId, OrderDTO orderDTO) {
        if (orderDTO.getFinalTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ParameterException(50011);
        }
        List<Long> skuIdList = orderDTO.getSkuInfoList().stream().map(SkuInfoDTO::getId).collect(Collectors.toList());

        List<Sku> skuList = iSkuService.getSkuListByIds(skuIdList);

        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;
        if (couponId != null) {
            Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new NotFountException(40004));
            userCouponRepository.findFirstByUserIdAndCouponIdAndStatusAndOrderIdIsNull(userId, couponId, CouponStatus.AVAILABLE.getCode()).orElseThrow(() -> new NotFountException(50006));
            couponChecker = new CouponChecker(coupon);
        }
        OrderChecker orderChecker = new OrderChecker(orderDTO, skuList, couponChecker, maxSkuLimit);
        orderChecker.isOk();
        return orderChecker;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long placeOrder(Long userId, OrderDTO orderDTO, OrderChecker orderChecker) {
        String orderNo = OrderUtils.makeOrderNo();
        DateTime now = DateTime.now();
        DateTime expiredTime = now.plusSeconds(payTimeLimit);
        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(userId)
                .totalCount(orderChecker.getTotalCount())
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID.getCode())
                .placedTime(now.toDate())
                .expiredTime(expiredTime.toDate())
                .build();
        order.setSnapAddress(orderDTO.getAddress());
        order.setSnapItems(orderChecker.getOrderSkuList());
        order.setCreateTime(now.toDate());
        orderRepository.save(order);
        //减库存
        reduceStock(orderChecker);
        //核销优惠券
        if (orderDTO.getCouponId() != null) {
            writeOffCoupon(orderDTO.getCouponId(), order.getId(), userId);
        }
        //TODO 加入至延迟消息队列
        return order.getId();
    }

    @Override
    public Page<Order> getUnpaid(int pageNum, Integer count) {
        Pageable pageable = PageRequest.of(pageNum, count, Sort.by("createTime").descending());
        Long userId = LocalUser.getUser().getId();
        DateTime now = DateTime.now();
        return orderRepository.findByExpiredTimeGreaterThanAndStatusAndUserId(now.toDate(), OrderStatus.UNPAID.getCode(), userId, pageable);
    }

    @Override
    public Page<Order> getByStatus(int status, int pageNum, Integer count) {
        Pageable pageable = PageRequest.of(pageNum, count, Sort.by("createTime").descending());
        Long uid = LocalUser.getUser().getId();
        if (status == OrderStatus.All.getCode()) {
            return orderRepository.findByUserId(uid, pageable);
        }
        return orderRepository.findByUserIdAndStatus(uid, status, pageable);
    }

    @Override
    public Optional<Order> getOrderDetail(Long orderId) {
        Long userId = LocalUser.getUser().getId();
        return orderRepository.findFirstByUserIdAndId(userId, orderId);
    }

    @Override
    public Order updateOrderPrepayId(Long orderId, String prePayId) {
        Long userId = LocalUser.getUser().getId();
        Optional<Order> orderOptional = orderRepository.findFirstByUserIdAndId(userId, orderId);
        AtomicReference<Order> save = new AtomicReference<>(null);
        orderOptional.ifPresent(order -> {
            order.setPrepayId(prePayId);
            save.set(orderRepository.save(order));
        });
        orderOptional.orElseThrow(() -> new ParameterException(10007));
        return save.get();
    }

    /**
     * description: 减库存
     *
     * @param orderChecker 下单的订单信息
     * @author k
     * @date 2020/06/07 18:53
     **/
    private void reduceStock(OrderChecker orderChecker) {
        List<OrderSku> orderSkuList = orderChecker.getOrderSkuList();
        for (OrderSku orderSku : orderSkuList) {
            int row = skuRepository.reduceStock(orderSku.getId(), Long.valueOf(orderSku.getCount()));
            if (row != 1) {
                throw new ParameterException(50003);
            }
        }
    }

    private void writeOffCoupon(Long couponId, Long orderId, Long userId) {
        int row = userCouponRepository.writeOff(couponId, orderId, userId);
        if (row != 1) {
            throw new ForbiddenException(40012);
        }
    }
}
