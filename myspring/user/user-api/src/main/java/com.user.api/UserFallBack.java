package com.user.api;

import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserApi {
    @Override
    public String testUser() {
        return "testUser fail";
    }

    @Override
    public String getOrderPort() {
        return "getOrderPort fail";
    }

    @Override
    public String getFeignReq() {
        return "getFeignReq fail";
    }

    @Override
    public String high() {
        return "high fail";
    }
}
