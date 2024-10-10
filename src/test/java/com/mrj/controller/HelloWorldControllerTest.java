package com.mrj.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@Slf4j
public class HelloWorldControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldControllerTest.class);

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    @DisplayName("Test HelloWorldController Responds With Text Hello From Service")
    public void testHelloWorldControllerRespondsWithTextHelloFromService() {
        var response = httpClient.toBlocking().retrieve("/hello");
        LOG.trace("Response: {}", response);
        assertEquals("Hello From Service!", response);
    }

    @Test
    @DisplayName("Test HelloWorldController Responds With Status Code 200")
    public void testHelloWorldControllerRespondsWithStatusCode200() {
        var response = httpClient.toBlocking().exchange("/hello", String.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello From Service!", response.getBody().get());
    }

}