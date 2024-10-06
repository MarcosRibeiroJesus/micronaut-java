package com.mrj.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void testHelloWorldControllerRespondsWithTextHelloWorld() {
        var response = httpClient.toBlocking().retrieve("/hello");

        assertEquals("Hello World", response);
    }

    @Test
    public void testHelloWorldControllerRespondsWithStatusCode200() {
        var response = httpClient.toBlocking().exchange("/hello", String.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello World", response.getBody().get());
    }

}