package com.softd.test.springcloud.userservice.myhystrixdefine;

public interface MyHystrixCommand<T> {

    T run();

    T fallBack();

}
