package com.example.rabbit.rabbitdemo.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
public class MsgLogDto implements Serializable {
    public MsgLogDto(String msgId, String exchangeName, String roteKeyName, String queueName, String status){
        this.msgId = msgId;
        this.exchangeName = exchangeName;
        this.roteKeyName = roteKeyName;
        this.queueName = queueName;
        this.status = status;
    }
    private String msgId;
    private String exchangeName;
    private String queueName;
    private String roteKeyName;
    private String status;
    private int count;
    private MailDto mailDto;

    public MailDto getMailDto() {
        return mailDto;
    }

    public void setMailDto(MailDto mailDto) {
        this.mailDto = mailDto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoteKeyName() {
        return roteKeyName;
    }

    public void setRoteKeyName(String roteKeyName) {
        this.roteKeyName = roteKeyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
