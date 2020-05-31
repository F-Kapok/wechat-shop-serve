package com.fans.vo;

import com.fans.utils.ObjectUtils;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * className: PagingDozer
 *
 * @author k
 * @version 1.0
 * @description 分页参数VO拷贝
 * @date 2020-05-30 21:01
 **/
@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class PagingDozer<S, T> extends Paging {

    public PagingDozer(Page<S> page, Class<T> tClass) {
        this.initPageParameters(page);
        List<S> sourceList = page.getContent();
        List<T> voList = Lists.newArrayList();
        sourceList.forEach(source -> voList.add(ObjectUtils.copyProperties(source, tClass)));
        this.setItems(voList);
    }
}
