package com.fans.repository;

import com.fans.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * interfaceName: ThemeRepository
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:45
 **/
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query(value = "select t from Theme t where t.name in (:names )")
    List<Theme> findByNames(List<String> names);

    Optional<Theme> findByName(String name);
}
