package com.fans.service.impl;

import com.fans.entity.Spu;
import com.fans.repository.SpuRepository;
import com.fans.service.ISearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * className: SearchServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:58
 **/
@Service(value = "iSearchService")
public class SearchServiceImpl implements ISearchService {
    @Resource(type = SpuRepository.class)
    private SpuRepository spuRepository;

    @Override
    public Page<Spu> search(String queryKey, Integer pageNum, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        String likeQueryKey = "%" + queryKey + "%";
        return spuRepository.findByTitleLikeOrSubtitleLike(likeQueryKey,likeQueryKey, paging);
    }
}
