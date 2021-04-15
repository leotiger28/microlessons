package ru.leo.springdemo.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageListener {

    private Logger logger = LogManager.getLogger(MessageListener.class);

    //protected final Logger logger = Logger.getLogger(MessageListener.class);

    @StreamListener(ConsumerChannels.DIRECTED)
    public void directed(String message) {
        logger.debug("Сообщение получателю : " + message);
    }

    @StreamListener(ConsumerChannels.BROADCASTS)
    public void broadcasted(String message) {
        logger.debug("Сообщение всем : " + message);
    }
}

