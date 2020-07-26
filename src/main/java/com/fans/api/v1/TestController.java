package com.fans.api.v1;

import com.fans.common.JsonData;
import com.fans.manager.rocketmq.ProducerSchedule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Resource(name = "producerSchedule")
    private ProducerSchedule producerSchedule;
    @Value(value = "${rocketmq.topic}")
    private String topic;

    @RequestMapping
    public JsonData<String> test() {
        return JsonData.success(test + "");
    }

    @GetMapping(value = "/send")
    public JsonData<String> sendMessage() {
        return JsonData.success(producerSchedule.sendMessage(topic, "hello rocketMQ！！！"));
    }
}
