package com.fans.service.impl;

import com.fans.entity.Tag;
import com.fans.repository.TagRepository;
import com.fans.service.ITagService;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * className: TagServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:39
 **/
@Service(value = "iTagService")
public class TagServiceImpl implements ITagService {
    @Resource(type = TagRepository.class)
    private TagRepository tagRepository;

    @Override
    public List<Tag> getTagInfoListByType(Byte type) {
        return tagRepository.findAllByType(type);
    }
}
