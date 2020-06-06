package com.fans.repository;

import com.fans.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interfaceName: SkuRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-06 23:18
 **/
@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {

    List<Sku> findByIdIn(List<Long> skuIds);
}
