package com.example.user.controller;

import com.order.api.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/testUser")
    public String testUser(){
        return "testUser Success";
    }

    @RequestMapping("/getOrderPort")
    public String getOrderPort(){
        //http://order/getPort【order为服务名称，spring.application.name】
        // 若想通过服务名称调用服务，需要在初始化restTemplate的时候添加  @LoadBalanced标签，在使用restTemplate时添加了一个拦截器，
        //拦截器进行ip:port替换，可以将url中的服务名称转换成具体的服务地址
        return restTemplate.getForObject("http://order/getPort",String.class);
    }

    @RequestMapping("/high")
    public String high(){
        return restTemplate.getForObject("http://order/highRequest",String.class);
    }
}
