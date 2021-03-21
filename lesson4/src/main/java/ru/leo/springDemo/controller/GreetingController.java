package ru.leo.springDemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.leo.springDemo.log.Loggable;
import ru.leo.springDemo.service.GreetingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    private final GreetingService greetingService;
    private List<Greeting> dbContent = new ArrayList<Greeting>() {{
        add(new Greeting("Вася"));
        add(new Greeting("Петя"));
        add(new Greeting("Коля"));
    }};

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
        for (Greeting g : dbContent) {
            greetingService.createGreeting(g);
        }
    }

    @Loggable
    @GetMapping("/greeting/{id}")
    public Greeting greeting(@PathVariable Long id) {
        return greetingService.findGreetingByID(id);
    }

    @Loggable
    @GetMapping("/greetings")
    public List<Greeting> greetings() {
        return greetingService.findAllGreetings();
    }

    @Loggable
    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestBody Map<String, String> bodyParams) {
        Greeting greeting = new Greeting(bodyParams.get("content"));
        return greetingService.createGreeting(greeting);
    }

    @Loggable
    @PutMapping("/greeting/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody Map<String, String> bodyParams) {
        Greeting greeting = new Greeting(bodyParams.get("content"));
        return greetingService.updateGreeting(id, greeting);
    }

    @Loggable
    @DeleteMapping("/greeting/{id}")
    public void deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
    }
}



