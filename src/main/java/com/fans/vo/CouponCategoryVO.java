package com.fans.vo;

import com.fans.entity.Category;
import com.fans.entity.Coupon;
import com.google.common.collect.Lists;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * className: CouponCategoryVO
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 17:15
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CouponCategoryVO extends CouponPureVO implements Serializable {

    private static final long serialVersionUID = -20200606171554L;

    private List<CategoryPureVO> categories = Lists.newArrayList();

    public CouponCategoryVO(Coupon coupon) {
        super(coupon);
        List<Category> categoryList = coupon.getCategoryList();
        categoryList.forEach(category -> {
            CategoryPureVO categoryPureVO = new CategoryPureVO(category);
            this.categories.add(categoryPureVO);
        });
    }
}
