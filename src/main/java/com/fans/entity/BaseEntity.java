package com.fans.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * className: BaseEntiry
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 13:39
 **/
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200530133900L;

    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date createTime;
    @JsonIgnore
    @Column(insertable = false, updatable = false)
    private Date updateTime;
    @JsonIgnore
    private Date deleteTime;

}
