package com.fans.repository;

import com.fans.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interfaceName: BannerRepository
 *
 * @author k
 * @version 1.0
 * @description banner数据操作层
 * @date 2020-05-30 13:54
 **/
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    Banner findByName(String name);
}
