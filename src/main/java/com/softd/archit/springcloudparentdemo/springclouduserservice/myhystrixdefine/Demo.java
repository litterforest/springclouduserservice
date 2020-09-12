package com.softd.archit.springcloudparentdemo.springclouduserservice.myhystrixdefine;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 自定义功能简单的超时熔断器
 *
 * @author cobee
 * @since 2020-09-10
 */
public class Demo {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        HelloCommand helloCommand = new HelloCommand();
        Future<String> stringFuture = executorService.submit(() -> {
            return helloCommand.run();
        });
        String result = null;
        try {
            result = stringFuture.get(100L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            result = helloCommand.fallBack();
        }
        System.out.println(result);
        executorService.shutdown();
    }
}
