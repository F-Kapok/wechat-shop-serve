package com.fans.api.v1;

import com.fans.entity.Tag;
import com.fans.service.ITagService;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * className: TagController
 *
 * @author k
 * @version 1.0
 * @description 热门标签控制层
 * @date 2020-06-29 20:34
 **/
@RestController
@RequestMapping(value = "/tag")
public class TagController {
    @Resource(name = "iTagService")
    private ITagService iTagService;

    @GetMapping(value = "/type/{type}")
    public List<Tag> getTagInfoList(@PathVariable(name = "type") Byte type) {
        List<Tag> tagList = iTagService.getTagInfoListByType(type);
        return tagList.isEmpty() ? Lists.newArrayList() : tagList;
    }
}
