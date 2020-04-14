package com.example.rabbit.rabbitdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.rabbit.rabbitdemo.dto.MailDto;
import com.example.rabbit.rabbitdemo.dto.MsgLogDto;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MailConsumer {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MailUtils mailUtils;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public  void consumerMsg(Message message, Channel channel) throws IOException {
        String str = new String(message.getBody());
        MailDto mailDto =JSONObject.parseObject(str,MailDto.class);
//        MailDto mailDto = (MailDto)JSONObject.parse(message.getBody().toString());
        System.out.println("收到消息："+mailDto.toString());
        String msgId = mailDto.getMsgId();
        MsgLogDto msgLogDto = (MsgLogDto)redisTemplate.opsForValue().get(msgId);
        //判断是否已经消费当前消息,保证幂等性
        if (msgLogDto == null || "1".equals(msgLogDto.getStatus())) {
            System.out.println("重复消费："+msgId);
            return;
        }
        MessageProperties messageProperties =message.getMessageProperties();
        long tag = messageProperties.getDeliveryTag();

        boolean success = mailUtils.sendMail(mailDto);
        if (success) {
            msgLogDto.setStatus("1");
            redisTemplate.opsForValue().set(msgId,msgLogDto);
            channel.basicAck(tag,false);//消费确认？？？？手动ack
        }else {
            channel.basicNack(tag,false , true);//
        }

    }
}
