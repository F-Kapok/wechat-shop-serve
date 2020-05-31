package com.fans.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * className: SpuDetailImg
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 19:46
 **/
@Entity
@Table(name = "spu_detail_img", schema = "wechat", catalog = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SpuDetailImg implements Serializable {

    private static final long serialVersionUID = -20200530194642L;
    @Id
    private Long id;
    private String img;
    @Column(name = "spu_id")
    private Long spuId;
    private Long index;

}