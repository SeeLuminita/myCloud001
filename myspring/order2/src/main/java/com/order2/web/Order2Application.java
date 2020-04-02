package com.order2.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Order2Application {

    public static void main(String[] args) {
        SpringApplication.run(Order2Application.class, args);
    }

}
