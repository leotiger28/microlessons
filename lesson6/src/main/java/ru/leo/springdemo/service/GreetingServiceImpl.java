package ru.leo.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.leo.springdemo.entity.Greeting;
import ru.leo.springdemo.exception.GreetingNotFoundException;
import ru.leo.springdemo.repository.GreetinRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GreetingServiceImpl implements GreetingService {

    private final GreetinRepository greetinRepository;

    @Autowired
    public GreetingServiceImpl(GreetinRepository greetinRepository) {
        this.greetinRepository = greetinRepository;
    }

    @Override
    public Greeting createGreeting(Greeting greeting) {
        return greetinRepository.save(greeting);
    }

    @Override
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    public Greeting updateGreeting(Long id, Greeting greeting) {
        Optional<Greeting> foundGreeting = greetinRepository.findById(id);
        if (foundGreeting.isPresent()) {
            foundGreeting.get().setUser(greeting.getUser());
            return greetinRepository.save(foundGreeting.get());
        } else throw new GreetingNotFoundException();
    }

    @Override
    public void deleteGreeting(Long id) {
        Optional<Greeting> foundGreeting = greetinRepository.findById(id);
        if (foundGreeting.isPresent()) greetinRepository.delete(foundGreeting.get());
        else throw new GreetingNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Greeting> findAllGreetings() {
        return greetinRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Greeting findGreetingByID(Long id) {
        return greetinRepository.findById(id).get();
    }

}
