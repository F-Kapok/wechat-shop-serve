package com.fans.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * className: SpuImg
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 19:46
 **/
@Entity
@Table(name = "spu_img", schema = "wechat", catalog = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SpuImg implements Serializable {

    private static final long serialVersionUID = -20200530194643L;

    @Id
    private Long id;
    private String img;
    @Column(name = "spu_id")
    private Long spuId;

}