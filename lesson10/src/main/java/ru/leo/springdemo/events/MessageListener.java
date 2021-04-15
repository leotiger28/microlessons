package ru.leo.springdemo.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import ru.leo.springdemo.entity.Greeting;
import ru.leo.springdemo.service.GreetingServiceImpl;

@Configuration
public class MessageListener {
    @Autowired
    GreetingServiceImpl service;

    private Logger logger = LogManager.getLogger(MessageListener.class);

    //protected final Logger logger = Logger.getLogger(MessageListener.class);

    @StreamListener(ConsumerChannels.DIRECTED)
    public void directed(String message) {
        logger.debug("Сообщение получателю : " + message);
        Greeting greeting = new Greeting(message);
        service.createGreeting(greeting);
    }

    @StreamListener(ConsumerChannels.BROADCASTS)
    public void broadcasted(String message) {
        logger.debug("Сообщение всем : " + message);
        Greeting greeting = new Greeting(message);
        service.createGreeting(greeting);
    }
}

