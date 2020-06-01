package com.fans.api.v1;

import com.fans.annotation.ScopeLevel;
import com.fans.core.exception.http.NotFountException;
import com.fans.entity.Banner;
import com.fans.service.IBannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * className: BannerController
 *
 * @author k
 * @version 1.0
 * @description 轮播图控制层
 * @date 2020-05-24 15:30
 **/
@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource(name = "iBannerService")
    private IBannerService iBannerService;

    @GetMapping(value = "/name/{name}")
    @ScopeLevel(value = 4)
    public Banner getBannerByName(@PathVariable(name = "name") String name) {
        Banner banner = iBannerService.getBannerByName(name);
        if (banner == null) {
            throw new NotFountException(30005);
        }
        return banner;
    }

}
