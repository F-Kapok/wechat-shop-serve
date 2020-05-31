package com.fans.service;

import com.fans.entity.Theme;

import java.util.List;
import java.util.Optional;

/**
 * interfaceName: IThemeService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:43
 **/
public interface IThemeService {

    List<Theme> findByNames(List<String> nameList);

    Optional<Theme> findByName(String name);
}
