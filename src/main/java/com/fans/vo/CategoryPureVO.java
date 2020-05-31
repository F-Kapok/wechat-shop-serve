package com.fans.vo;

import com.fans.entity.Category;
import com.fans.utils.ObjectUtils;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * className: CategoryPureVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:00
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CategoryPureVO implements Serializable {

    private static final long serialVersionUID = -20200531160020L;

    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private Long parentId;
    private String img;
    private Integer index;
    private Integer online;
    private Integer level;

    public CategoryPureVO(Category category) {
        BeanUtils.copyProperties(category, this);
    }
}
