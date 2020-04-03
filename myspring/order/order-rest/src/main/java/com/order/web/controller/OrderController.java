package com.order.web.controller;

import com.order.api.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController implements OrderApi {
    @Value("${server.port}")
    private String localPort;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @RequestMapping("/getPort")
    public String getPort() throws Exception{
        InetAddress inetAddress =  InetAddress.getLocalHost();
        return inetAddress.getHostAddress()+":"+this.localPort;
    }

    @RequestMapping("/getFeignClient")
    public String getFeignClient(){
        return "feignClient"+this.localPort;
    }

    @Override
    public String highRequest() {
        //获取锁
        boolean lock =  redisTemplate.opsForValue().setIfAbsent("test","9011",30, TimeUnit.SECONDS);
        if(!lock){
            return "fail";
        }else {
            String mes = "";
            try {
                //获取库存
                String countStr = redisTemplate.opsForValue().get("ordertest");
                int count = Integer.valueOf(countStr);
                if (count > 0) {
                    count = count-1;
                    countStr = String.valueOf(count);
                    redisTemplate.opsForValue().set("ordertest",countStr);
                    System.out.println(this.localPort+"端购买成功,剩余库存量===>"+countStr);
                    mes = this.localPort+"端购买成功,剩余库存量===>"+countStr;
                }else{
                    System.out.println("库存不足");
                    mes = "fail";
                }
            }catch (Exception e){

            }finally {
                redisTemplate.delete("test");
                return  mes;
            }
        }
    }
}
