package com.fans.repository;

import com.fans.entity.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interfaceName: SpuRepository
 *
 * @author k
 * @version 1.0
 * @description spu数据操作层
 * @date 2020-05-30 16:50
 **/
@Repository
public interface SpuRepository extends JpaRepository<Spu, Long> {

    Spu findOneById(Long id);

    /**
     * description: 查询二级分类列表
     *
     * @param categoryId 分类Id
     * @param pageable   分页参数
     * @return org.springframework.data.domain.Page<com.fans.entity.Spu>
     * @author k
     * @date 2020/05/30 22:33
     **/
    Page<Spu> findByCategoryId(Long categoryId, Pageable pageable);
    /**
     * description: 查询一级分类列表
     *
     * @param categoryId 分类Id
     * @param pageable   分页参数
     * @return org.springframework.data.domain.Page<com.fans.entity.Spu>
     * @author k
     * @date 2020/05/30 22:33
     **/
    Page<Spu> findByRootCategoryId(Long categoryId, Pageable pageable);

}
