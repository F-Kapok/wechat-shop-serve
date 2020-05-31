package com.fans.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fans.entity.Category;
import com.fans.repository.CategoryRepository;
import com.fans.service.ICategoryService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * className: CategoryServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:36
 **/
@Service(value = "iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Resource(type = CategoryRepository.class)
    private CategoryRepository categoryRepository;

    @Override
    public Map<Integer, List<Category>> getAllCategories() {
        List<Category> roots = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        List<Category> subs = categoryRepository.findAllByIsRootOrderByIndexAsc(false);
        Map<Integer, List<Category>> map = Maps.newHashMap();
        map.put(1, roots);
        map.put(2, subs);
        return map;
    }
}
