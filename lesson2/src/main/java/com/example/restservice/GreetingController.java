package com.example.restservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Здравствуй, ученик, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")

    @Loggable
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        if(name.equals("logError") ) {
            throw new RuntimeException("throw logError");
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
