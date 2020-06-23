package com.fans.service;

import com.fans.entity.Spu;
import org.springframework.data.domain.Page;

/**
 * interfaceName: ISpuService
 *
 * @author k
 * @version 1.0
 * @description spi服务层
 * @date 2020-05-30 16:50
 **/
public interface ISpuService {

    Spu getSpuById(Long id);

    Page<Spu> getLatestPagingSpu(Integer pageNum, Integer size);

    Page<Spu> getHotLatestPagingSpu(Integer pageNum, Integer size);

    Page<Spu> getByCategory(Long categoryId, Boolean isRoot, Integer pageNum, Integer size);
}
