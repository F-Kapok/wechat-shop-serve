package com.fans.service.impl;

import com.fans.entity.Theme;
import com.fans.repository.ThemeRepository;
import com.fans.service.IThemeService;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * className: ThemeServiceImpl
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-31 16:43
 **/
@Service(value = "iThemeService")
public class ThemeServiceImpl implements IThemeService {

    @Resource(type = ThemeRepository.class)
    private ThemeRepository themeRepository;

    @Override
    public List<Theme> findByNames(List<String> nameList) {
        return themeRepository.findByNames(nameList);
    }

    @Override
    public Optional<Theme> findByName(String name) {
        return themeRepository.findByName(name);
    }

}
