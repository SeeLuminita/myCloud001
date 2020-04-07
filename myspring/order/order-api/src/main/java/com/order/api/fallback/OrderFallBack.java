package com.order.api.fallback;

import com.order.api.OrderApi;
import org.springframework.stereotype.Component;

//@Component
public class OrderFallBack implements OrderApi {
    @Override
    public String getPort() throws Exception {
        return "error";
    }

    @Override
    public String getFeignClient() {
        return  "error";
    }

    @Override
    public String highRequest() {
        return  "error";
    }
}
