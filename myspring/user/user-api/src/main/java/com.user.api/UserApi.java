package com.user.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user", fallback = UserFallBack.class)
public interface UserApi {
    @RequestMapping(value = "/testUser",method = RequestMethod.GET)
    String testUser();

    @RequestMapping(value = "/getOrderPort",method = RequestMethod.GET)
    String getOrderPort();

    @RequestMapping(value = "/getFeignReq",method = RequestMethod.GET)
    String getFeignReq() throws Exception;

    @RequestMapping(value = "/high",method = RequestMethod.GET)
    String high();
}
