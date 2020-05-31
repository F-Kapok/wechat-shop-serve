package com.fans.api.v1;

import com.fans.core.exception.http.NotFountException;
import com.fans.entity.Theme;
import com.fans.service.IThemeService;
import com.fans.utils.ObjectUtils;
import com.fans.vo.ThemePureVO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * className: ThemeController
 *
 * @author k
 * @version 1.0
 * @description 主题控制层
 * @date 2020-05-30 16:03
 **/
@RestController
@RequestMapping(value = "/theme")
@Validated
public class ThemeController {

    @Resource(name = "iThemeService")
    private IThemeService iThemeService;

    @GetMapping(value = "/by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name = "names") String names) {
        List<String> nameList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(names);
        List<Theme> themes = iThemeService.findByNames(nameList);
        List<ThemePureVO> list = Lists.newArrayList();
        themes.forEach(theme -> list.add(ObjectUtils.copyProperties(theme, ThemePureVO.class)));
        return list;
    }

    @GetMapping(value = "/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName) {
        Optional<Theme> theme = iThemeService.findByName(themeName);
        return theme.orElseThrow(() -> new NotFountException(30003));
    }
}
