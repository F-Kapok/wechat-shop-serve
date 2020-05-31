package com.fans.core.configuration;

import com.fans.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * className: AutoPrefixConfiguration
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-05-25 21:58
 **/
@Configuration
public class AutoPrefixConfiguration implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixUrlMapping();
    }
}
