package com.mrj.service;

import jakarta.inject.Singleton;

@Singleton
public class HelloWorldService {

    public String helloFromService() {
        return "Hello From Service!";
    }
}
