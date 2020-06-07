package com.fans.entity;

import com.fans.dto.OrderAddressDTO;
import com.fans.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * className: Order
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 18:04
 **/
@Table(name = "`order`")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null")
public class Order extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200607180425L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal totalPrice;
    private Integer totalCount;
    private Date expiredTime;
    private Date placedTime;
    private String snapImg;
    private String snapTitle;
    private String snapItems;
    private String snapAddress;
    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Integer status;

    public void setSnapItems(List<OrderSku> orderSkuList) {
        this.snapItems = JsonUtils.obj2String(orderSkuList);
    }

    public List<OrderSku> getSnapItems() {
        return JsonUtils.string2Obj(snapItems, new TypeReference<List<OrderSku>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
    }

    public OrderAddressDTO getSnapAddress() {
        return JsonUtils.string2Obj(snapAddress, OrderAddressDTO.class);
    }

    public void setSnapAddress(OrderAddressDTO orderAddressDTO) {
        this.snapAddress = JsonUtils.obj2String(orderAddressDTO);
    }
}