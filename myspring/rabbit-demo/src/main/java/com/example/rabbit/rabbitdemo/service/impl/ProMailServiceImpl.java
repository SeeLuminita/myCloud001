package com.example.rabbit.rabbitdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbit.rabbitdemo.dto.MailDto;
import com.example.rabbit.rabbitdemo.dto.MsgLogDto;
import com.example.rabbit.rabbitdemo.service.ProMailService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ProMailServiceImpl implements ProMailService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public String send(){
        String msgId = UUID.randomUUID().toString();
        String exchangeName = RabbitConfig.MAIL_EXCHANGE_NAME;
        String routeKeyName = RabbitConfig.MAIL_ROUTING_KEY_NAME;
        String queueName = RabbitConfig.MAIL_QUEUE_NAME;
        MsgLogDto msgLogDto = new MsgLogDto(msgId,exchangeName,routeKeyName,queueName,"");
        redisTemplate.opsForValue().set(msgId,msgLogDto);
        CorrelationData correlationData = new CorrelationData(msgId);
        //封装消息
        MailDto mailDto = new MailDto();
        mailDto.setMsgId(msgId);
        mailDto.setContent(msgId+">>>>>>this is new  test content:"+new Date().getTime());
        System.out.println("content:"+mailDto.getContent());
        String jsonString = JSONObject.toJSONString(mailDto);
        Message message = MessageBuilder.withBody(jsonString.getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME,RabbitConfig.MAIL_ROUTING_KEY_NAME,
               message,correlationData);
        return "success";
    }
}
