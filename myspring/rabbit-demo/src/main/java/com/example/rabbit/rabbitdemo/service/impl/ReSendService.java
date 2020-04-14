package com.example.rabbit.rabbitdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbit.rabbitdemo.dto.MsgLogDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReSendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    //最大发送次数为3
    private static final  int MAX_SEND_COUNT=3;

    @Scheduled(cron = "0/30 * * * * ?")
    public void reSend(){
        System.out.println("开始执行定时任务……");
        //获取当前redis中所有的消息
        List<MsgLogDto> msgLogDtos = (List<MsgLogDto>) redisTemplate.opsForValue().get("msgs");
        msgLogDtos.stream().filter(a->"".equals(a.getStatus())).forEach(msgLogDto -> {
            int count = msgLogDto.getCount();
            String msgId = msgLogDto.getMsgId();
            if (count >= MAX_SEND_COUNT) {
                System.out.println(msgId+"重复发送超过了最大次数,发送失败!");
                msgLogDto.setStatus("2");
            }else {
                msgLogDto.setCount(count+1);
                CorrelationData correlationData = new CorrelationData(msgId);
                String jsonString = JSONObject.toJSONString(msgLogDto.getMailDto());
                Message message = MessageBuilder.withBody(jsonString.getBytes()).build();
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
                message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
                rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME,RabbitConfig.MAIL_ROUTING_KEY_NAME,
                        message,correlationData);
                System.out.println("第"+count+1+"次重新推送消息");
            }
        });
        System.out.println("执行定时任务结束……");
    }
}
