package com.mrj.service.Impl;

import com.mrj.service.HelloWorldService;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Primary
@Singleton
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String helloFromService() {
        return "Hello From Service!";
    }
}
