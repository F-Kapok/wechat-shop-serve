package com.fans.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * className: AutoPrefixUrlMapping
 *
 * @author k
 * @version 1.0
 * @description 路径url
 * @date 2020-05-25 21:54
 **/
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Value(value = "${kapok.api-package}")
    private String apiPackagePath;


    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (mappingInfo != null) {
            String prefix = this.getPrefix(handlerType);
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return null;
    }

    private String getPrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName.replaceAll(apiPackagePath, "");
        return dotPath.replace(".", "/");
    }
}
