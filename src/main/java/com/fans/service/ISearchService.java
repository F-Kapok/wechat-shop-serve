package com.fans.service;

import com.fans.entity.Spu;
import org.springframework.data.domain.Page;

/**
 * interfaceName: ISearchService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:57
 **/
public interface ISearchService {
    Page<Spu> search(String queryKey, Integer pageNum, Integer pageSize);
}
