package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * className: SaleExplain
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 16:24
 **/
@Entity
@Table(name = "sale_explain", schema = "wechat", catalog = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null and fixed = 1")
public class SaleExplain extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200629162414L;
    @Id
    private Long id;
    private Byte fixed;
    private String text;
    private Long spuId;
    private Integer index;
    private Integer replaceId;


}
