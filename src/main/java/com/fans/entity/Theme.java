package com.fans.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * className: Theme
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:27
 **/
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Where(clause = "delete_time is null")
public class Theme extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -20200531162714L;

    @Id
    private Long id;
    private String title;
    private String description;
    private String name;
    private String tplName;
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private Boolean online;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "theme_spu", joinColumns = {@JoinColumn(name = "theme_id")},
            inverseJoinColumns = {@JoinColumn(name = "spu_id")})
    private List<Spu> spuList;
}
