package com.fans.api.v1;

import com.fans.common.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * className: TestController
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-07 00:48
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource(name = "testService")
    private TestService test;

    @RequestMapping
    public JsonData<String> test() {
        return JsonData.success(test + "");
    }
}
