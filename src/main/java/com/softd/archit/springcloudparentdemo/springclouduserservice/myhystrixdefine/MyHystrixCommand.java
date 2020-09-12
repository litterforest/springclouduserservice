package com.softd.archit.springcloudparentdemo.springclouduserservice.myhystrixdefine;

public interface MyHystrixCommand<T> {

    T run();

    T fallBack();

}
