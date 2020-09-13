package com.softd.archit.springcloudparentdemo.springclouduserservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-09-13
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserOrderInfoFallback")
    public String getUserOrderInfo(String orderId){
        return restTemplate.getForObject("http://orderservice/order/getOrder/{orderId}", String.class, orderId);
    }

    private String getUserOrderInfoFallback(String orderId){
        return "返回降级处理，orderId:" + orderId;
    }

}
