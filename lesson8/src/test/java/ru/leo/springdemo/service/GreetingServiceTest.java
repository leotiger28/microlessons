package ru.leo.springdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.leo.springdemo.entity.Greeting;
import ru.leo.springdemo.repository.GreetinRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GreetingServiceTest {

    @Autowired
    private GreetingServiceImpl service;
    @Autowired
    private GreetinRepository repository;

    @Test
    public void getDataJpaTest() {
        Greeting greeting = service.findGreetingByID(1L);
        assertEquals(greeting.getContent(), "Здравствуй, ученик, Ученик 1!");
    }

    @Test
    public void createDataJpaTest() {
        Greeting greeting = new Greeting("Leo Max");
        service.createGreeting(greeting);
        Greeting findGreeting = service.findGreetingByID(greeting.getId());
        assertTrue(findGreeting != null);
    }

    @Test
    public void updateDataJpaTest() {
        Greeting greeting = service.createGreeting(new Greeting("Leo Max 27"));
        service.updateGreeting(greeting.getId(),new Greeting("Leo Max 28"));
        Greeting findGreeting = service.findGreetingByID(greeting.getId());
        assertEquals(findGreeting.getContent(), "Здравствуй, ученик, Leo Max 28!");
    }

    @Test
    public void deleteDataJpaTest() {
        service.deleteGreeting(2L);
        assertTrue(service.findGreetingByID(2L) == null);
    }

    @TestConfiguration
    static class GreetingServiceImplTestContextConfiguration {

        @Bean
        public GreetingServiceImpl service(GreetinRepository greetingRepository) {
            return new GreetingServiceImpl(greetingRepository);
        }
    }
}