package com.fans.repository;

import com.fans.entity.GridCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interfaceName: GridCategoryRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:20
 **/
@Repository
public interface GridCategoryRepository extends JpaRepository<GridCategory,Long> {
}
