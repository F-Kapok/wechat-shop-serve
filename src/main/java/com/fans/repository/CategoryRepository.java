package com.fans.repository;

import com.fans.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interfaceName: CategoryRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 15:38
 **/
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByIsRootOrderByIndexAsc(boolean isRoot);
}
