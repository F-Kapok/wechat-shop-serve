package com.fans.repository;

import com.fans.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query(value = "update Sku s set s.stock = s.stock - :quantity\n" +
            "where s.id = :skuId\n" +
            "and s.stock >= :quantity")
    int reduceStock(@Param("skuId") Long skuId, @Param("quantity") Long quantity);

    @Modifying
    @Query(value = "update Sku s set s.stock = s.stock + (:quantity) where s.id = :skuId\n")
    int recoverStock(@Param("skuId") Long skuId, @Param("quantity") Long quantity);
}
