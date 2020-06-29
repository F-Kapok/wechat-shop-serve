package com.fans.service;

import com.fans.entity.Tag;

import java.util.List;

/**
 * interfaceName: ITagService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:38
 **/
public interface ITagService {

    List<Tag> getTagInfoListByType(Byte type);
}
