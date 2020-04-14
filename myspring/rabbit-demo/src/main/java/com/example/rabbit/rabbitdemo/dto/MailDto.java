package com.example.rabbit.rabbitdemo.dto;

import java.io.Serializable;

public class MailDto implements Serializable {
    private String content;
    private String msgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
