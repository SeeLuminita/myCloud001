package com.user.controller;

import com.user.api.UserApi;
import com.user.api.UserFallBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackFactory implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        System.out.println("UserApi");
        return new UserFallBack();
    }
}
