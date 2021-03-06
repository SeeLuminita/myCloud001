package com.user.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**最初在EnableFeignClients注解上没有添加basePackages属性，导致在userController中注入orderApi的时候一直找不到bean
需要在调用方basePackages属性上添加feignClient所在包的路径**/
@EnableFeignClients(basePackages = {"com.order.api"})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.order.api","com.user.web"})
//下面的注解作用等同于上面注解的作用
//@ComponentScan(basePackages = {"com.order.api","com.user.web"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
