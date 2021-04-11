package ru.leo.springDemo.service;

import ru.leo.springDemo.controller.Greeting;

import java.util.List;

public interface GreetingService {
    Greeting createGreeting(Greeting greeting);
    Greeting updateGreeting(Long id, Greeting greeting);
    List<Greeting> findAllGreetings();
    Greeting findGreetingByID(Long id);
    void deleteGreeting(Long id);
}
