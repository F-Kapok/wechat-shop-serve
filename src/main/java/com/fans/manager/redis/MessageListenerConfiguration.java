package com.fans.manager.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

/**
 * className: MessageListenerConfiguration
 *
 * @author k
 * @version 1.0
 * @description redis接收消息通知配置
 * @date 2020-07-19 18:09
 **/
@Configuration
public class MessageListenerConfiguration {

    @Value(value = "${spring.redis.listen-pattern}")
    private String pattern;

    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        Topic topic = new PatternTopic(pattern);
        container.addMessageListener(topicMessageListener(), topic);
        return container;
    }

    @Bean
    public TopicMessageListener topicMessageListener() {
        return new TopicMessageListener();
    }
}
