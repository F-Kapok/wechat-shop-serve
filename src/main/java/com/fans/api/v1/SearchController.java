package com.fans.api.v1;

import com.fans.entity.Spu;
import com.fans.service.ISearchService;
import com.fans.vo.PagingDozer;
import com.fans.vo.SpuSimplifyVO;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * className: SearchController
 *
 * @author k
 * @version 1.0
 * @description 搜索spu控制层
 * @date 2020-06-29 20:54
 **/
@RestController
@RequestMapping(value = "/search")
public class SearchController implements Serializable {

    @Resource(name = "iSearchService")
    private ISearchService iSearchService;

    @GetMapping
    public PagingDozer<Spu, SpuSimplifyVO> search(@RequestParam String q,
                                                  @RequestParam(defaultValue = "0") Integer start,
                                                  @RequestParam(defaultValue = "10") Integer count) {
        Page<Spu> page = iSearchService.search(q, start / count, count);
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }

}
