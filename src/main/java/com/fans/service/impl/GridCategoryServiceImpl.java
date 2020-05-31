package com.fans.service.impl;

import com.fans.entity.GridCategory;
import com.fans.repository.GridCategoryRepository;
import com.fans.service.IGridCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: GridCategoryServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:18
 **/
@Service(value = "iGridCategoryService")
public class GridCategoryServiceImpl implements IGridCategoryService {

    @Resource(type = GridCategoryRepository.class)
    private GridCategoryRepository gridCategoryRepository;

    @Override
    public List<GridCategory> getGridCategoryList() {
        return gridCategoryRepository.findAll();
    }
}
