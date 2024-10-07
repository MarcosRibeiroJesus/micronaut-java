package com.mrj.controller;

import com.mrj.service.HelloWorldService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@Controller("/hello")
@RequiredArgsConstructor
public class HelloWorldController {

    private final HelloWorldService service;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String hello() {
        return service.helloFromService();
    }
}
