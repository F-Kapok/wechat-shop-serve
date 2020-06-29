package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * className: Tag
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:32
 **/
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Where(clause = "delete_time is null")
public class Tag extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200629203238L;

    @Id
    private Long id;
    private String title;
    private String description;
    private Byte highlight;
    private Byte type;
}
