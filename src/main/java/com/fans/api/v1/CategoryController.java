package com.fans.api.v1;

import com.fans.core.exception.http.NotFountException;
import com.fans.entity.Category;
import com.fans.entity.GridCategory;
import com.fans.service.ICategoryService;
import com.fans.service.IGridCategoryService;
import com.fans.vo.CategoriesAllVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * className: CategoryController
 *
 * @author k
 * @version 1.0
 * @description 分类控制层
 * @date 2020-05-31 15:30
 **/
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Resource(name = "iCategoryService")
    private ICategoryService iCategoryService;

    @Resource(name = "iGridCategoryService")
    private IGridCategoryService iGridCategoryService;


    @GetMapping(value = "/all")
    public CategoriesAllVO getAllCategory() {
        Map<Integer, List<Category>> allCategories = iCategoryService.getAllCategories();
        return new CategoriesAllVO(allCategories);
    }


    @GetMapping(value = "/grid/all")
    public List<GridCategory> getGridCategoryList() {
        List<GridCategory> gridCategoryList = iGridCategoryService.getGridCategoryList();
        if (gridCategoryList.isEmpty()) {
            throw new NotFountException(30009);
        }
        return gridCategoryList;
    }
}
