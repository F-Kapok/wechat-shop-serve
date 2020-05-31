package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * className: GridCategory
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:13
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "grid_category", schema = "wechat")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Where(clause = "delete_time is null")
public class GridCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200531161310L;

    @Id
    private Long id;
    private String title;
    private String img;
    private String name;
    private Long categoryId;
    private Long rootCategoryId;

}
