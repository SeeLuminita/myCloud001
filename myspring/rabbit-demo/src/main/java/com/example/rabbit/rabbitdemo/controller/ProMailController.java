package com.example.rabbit.rabbitdemo.controller;

import com.example.rabbit.rabbitdemo.service.ProMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/promail")
@RestController
public class ProMailController {
    @Autowired
    private ProMailService proMailService;

    @RequestMapping(value = "/sendMail",method = RequestMethod.POST)
    public String sendMail(){
        return proMailService.send();
    }

}
