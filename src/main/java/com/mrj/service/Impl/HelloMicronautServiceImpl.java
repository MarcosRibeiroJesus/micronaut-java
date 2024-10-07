package com.mrj.service.Impl;

import com.mrj.service.HelloWorldService;
import jakarta.inject.Singleton;

@Singleton
public class HelloMicronautServiceImpl implements HelloWorldService {

    @Override
    public String helloFromService() {
        return "Hello From Micronaut!";
    }
}
