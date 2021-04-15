package ru.leo.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.leo.springdemo.events.ConsumerChannels;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableBinding(ConsumerChannels.class)
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
