package com.fans.manager.redis;

import com.alibaba.fastjson.JSON;
import com.fans.manager.redis.model.RedisMessageKey;
import com.fans.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import javax.annotation.Resource;

/**
 * className: TopicMessageListener
 *
 * @author k
 * @version 1.0
 * @description 接收redis消息通知类
 * @date 2020-07-19 18:06
 **/
@Slf4j
public class TopicMessageListener implements MessageListener {

    @Resource(type = ApplicationEventPublisher.class)
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        DateTime start = DateTime.now();
        log.info("------redis通知-----start------" + start.toString("yyyy-MM-dd HH:mm:ss") + "------");
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String redisMessageKey = new String(body);
        String topic = new String(channel);
        log.info("--> 通知key:{}", redisMessageKey);
        log.info("--> 消息通道:{}", topic);
        RedisMessageKey messageKey = JSON.parseObject(redisMessageKey, RedisMessageKey.class);
        applicationEventPublisher.publishEvent(messageKey);
        DateTime end = DateTime.now();
        log.info("------redis通知-----end------" + end.toString("yyyy-MM-dd HH:mm:ss") + "------");
        log.info("--> 耗时:{}", end.getMillis() - start.getMillis() + "ms");
    }

}
