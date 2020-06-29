package com.fans.repository;

import com.fans.entity.SaleExplain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * interfaceName: SaleExplainRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-29 16:34
 **/
@Repository
public interface SaleExplainRepository extends JpaRepository<SaleExplain, Long> {

    List<SaleExplain> findBySpuId(Long spuId);

}
