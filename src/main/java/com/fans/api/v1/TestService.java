package com.fans.api.v1;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * className: TestService
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 00:49
 **/
@Component(value = "testService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestService {


}
