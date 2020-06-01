package com.fans.service.impl;

import com.fans.entity.Spu;
import com.fans.repository.SpuRepository;
import com.fans.service.ISpuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * className: SpuServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-30 16:50
 **/
@Service(value = "iSpuService")
public class SpuServiceImpl implements ISpuService {

    @Resource(type = SpuRepository.class)
    private SpuRepository spuRepository;

    @Override
    public Spu getSpuById(Long id) {
        return spuRepository.findOneById(id);
    }

    @Override
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size) {
        Pageable pageRequest = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        return spuRepository.findAll(pageRequest);
    }

    @Override
    public Page<Spu> getByCategory(Long categoryId, Boolean isRoot, Integer pageNum, Integer size) {
        Pageable pageable = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        if (isRoot) {
            return spuRepository.findByRootCategoryId(categoryId, pageable);
        }
        return spuRepository.findByCategoryId(categoryId, pageable);
    }
}
