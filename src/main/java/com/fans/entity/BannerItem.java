package com.fans.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * className: BannerItem
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 13:32
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "banner_item", schema = "wechat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BannerItem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200530133245L;

    @Id
    private Long id;
    private String img;
    private String keyword;
    private short type;
    @Column(name = "banner_id")
    private Long bannerId;
    private String name;
}
