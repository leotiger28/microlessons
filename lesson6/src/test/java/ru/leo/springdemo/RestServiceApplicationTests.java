package ru.leo.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.leo.springdemo.entity.Greeting;
import ru.leo.springdemo.controller.GreetingController;
import ru.leo.springdemo.repository.GreetinRepository;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class RestServiceApplicationTests {

    @Autowired
    private GreetinRepository repository;
    @Autowired
    private GreetingController controller;

    @Test
    public void contextLoads() {

        Map<String, String> map = new HashMap<>();
        map.put("id", "28");
        map.put("content", "LeoTiger");
        Greeting g = controller.createGreeting(map);
        map.put("content", "LeoTiger28");
        controller.updateGreeting(g.getId(), map);
        controller.greeting(g.getId());
        controller.greetings();
        //controller.deleteGreeting(g.getId());
        //controller.greetings();

    }

}