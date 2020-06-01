package com.fans.service;

import com.fans.entity.Category;

import java.util.List;
import java.util.Map;

/**
 * interfaceName: ICategoryService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:36
 **/
public interface ICategoryService {

    Map<Integer, List<Category>> getAllCategories();

}
