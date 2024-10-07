package com.mrj;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class MicronautStartTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    @DisplayName("Test App Works")
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

}
