package com.fans.vo;

import com.fans.entity.Category;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * className: CategoriesAllVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:35
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CategoriesAllVO implements Serializable {

    private static final long serialVersionUID = -20200531153506L;

    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer, List<Category>> map) {
        List<Category> roots = map.get(1);
        List<Category> subs = map.get(2);
        this.roots = roots.stream().map(CategoryPureVO::new).collect(Collectors.toList());
        this.subs = subs.stream().map(CategoryPureVO::new).collect(Collectors.toList());
    }
}
