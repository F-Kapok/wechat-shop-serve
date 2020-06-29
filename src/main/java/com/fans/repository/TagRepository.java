package com.fans.repository;

import com.fans.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interfaceName: TagRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 20:40
 **/
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByType(Byte type);
}
