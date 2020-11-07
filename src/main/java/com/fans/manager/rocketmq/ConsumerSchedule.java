package com.fans.manager.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * className: ConsumerSchedule
 *
 * @author k
 * @version 1.0
 * @description rocketMQ消费者
 * @date 2020-07-26 22:38
 **/
//@Component(value = "consumerSchedule")
@Slf4j
public class ConsumerSchedule implements CommandLineRunner {

    @Value(value = "${rocketmq.topic}")
    private String topic;
    @Value(value = "${rocketmq.consumer.consumer-group}")
    private String consumerGroup;
    @Value(value = "${rocketmq.namesrv-addr}")
    private String namesrvAddr;

    private DefaultMQPushConsumer consumer;


    public void messageListener() throws MQClientException {
        if (consumer == null) {
            consumer = new DefaultMQPushConsumer(consumerGroup);
        }
        //设置监听地址
        consumer.setNamesrvAddr(namesrvAddr);
        //设置监听通道
        consumer.subscribe(topic, "*");
        //设置消费同时的数量
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.registerMessageListener((MessageListenerConcurrently) (messageExtList, consumeConcurrentlyContext) -> {
            messageExtList.forEach(message -> log.info("--> 【{}】通道接收到消息:{}", topic, new String(message.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }


    @Override
    public void run(String... args) throws Exception {
//        this.messageListener();
    }
}
