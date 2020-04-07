package com.order.api;

import com.order.api.fallback.OrderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "order")
public interface OrderApi {
    ///负载均衡项目测试---restTemplate+ribbon
    @RequestMapping(method = RequestMethod.GET,value = "/getPort")
    String getPort() throws Exception;
    //负载均衡项目测试---feign
    @RequestMapping(method = RequestMethod.GET,value = "/getFeignClient")
    String getFeignClient() throws Exception;

    //高并发项目测试--redis
    @RequestMapping(method = RequestMethod.GET,value = "/highRequest")
    String highRequest();
}
