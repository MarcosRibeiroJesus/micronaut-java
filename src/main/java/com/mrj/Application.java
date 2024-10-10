package com.mrj;

import com.mrj.service.Impl.HelloMicronautServiceImpl;
import com.mrj.service.Impl.HelloWorldServiceImpl;
import io.micronaut.runtime.Micronaut;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        var context = Micronaut.run(Application.class, args);

        log.debug("Message from Hello World service: {}", context.getBean(HelloWorldServiceImpl.class).helloFromService());
        log.debug("Message from Micronaut service: {}", context.getBean(HelloMicronautServiceImpl.class).helloFromService());
    }
}