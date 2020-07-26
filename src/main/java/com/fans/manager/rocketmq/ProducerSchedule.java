package com.fans.manager.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * className: ProducerSchedule
 *
 * @author k
 * @version 1.0
 * @description rocketMQ生产者
 * start mqnamesrv.cmd
 * start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
 * @date 2020-07-26 18:58
 **/
@Component(value = "producerSchedule")
@Slf4j
public class ProducerSchedule {
    @Value(value = "${rocketmq.producer.producer-group}")
    private String producerGroup;
    @Value(value = "${rocketmq.namesrv-addr}")
    private String namesrvAddr;

    private DefaultMQProducer producer;

    @PostConstruct
    public void defaultRocketMq() {
        if (producer == null) {
            this.producer = new DefaultMQProducer(producerGroup);
            this.producer.setNamesrvAddr(namesrvAddr);
        }
        try {
            producer.start();
            log.info("---> rocketMQ producer 已启动 ！！！");
        } catch (MQClientException e) {
            e.printStackTrace();
            log.error("---> rocketMQ producer 启动失败，失败原因:{}", e.getMessage());
        }
    }

    public String sendMessage(String topic, String msg) {
        Message message = new Message(topic, msg.getBytes());
        // messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        message.setDelayTimeLevel(4);
        String statusName = StringUtils.EMPTY;
        try {
            SendResult send = producer.send(message);
            String msgId = send.getMsgId();
            SendStatus sendStatus = send.getSendStatus();
            statusName = sendStatus.name();
            log.info("--> 主题:【{}】 发送消息:【{}】成功, 消息ID:【{}】,发送状态:【{}】", topic, msg, msgId, statusName);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            log.error("--> 主题{} 发送消息 {} 失败,原因：{}", topic, msg, e.getMessage());
        }
        return statusName;
    }
}
