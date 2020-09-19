package com.softd.test.springcloud.userservice.service;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
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

    @HystrixCommand(fallbackMethod = "getUserOrderInfoFallback", commandProperties = {
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD")
    })
    public String getUserOrderInfo(String orderId){
        return restTemplate.getForObject("http://orderservice/order/getOrder/{orderId}", String.class, orderId);
    }

    private String getUserOrderInfoFallback(String orderId){
        return "返回降级处理，orderId:" + orderId;
    }

}
