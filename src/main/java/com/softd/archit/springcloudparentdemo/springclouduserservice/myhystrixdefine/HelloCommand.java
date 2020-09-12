package com.softd.archit.springcloudparentdemo.springclouduserservice.myhystrixdefine;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 功能描述
 *
 * @author cobee
 * @since 2020-09-10
 */
public class HelloCommand implements MyHystrixCommand<String> {

    @Override
    public String run() {
        int randomInt = ThreadLocalRandom.current().nextInt(150);
        System.out.println("休眠时间：" + randomInt + "ms");
        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "正常返回";
    }

    @Override
    public String fallBack() {
        return "降级返回";
    }
}
