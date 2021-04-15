package ru.leo.springdemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.leo.springdemo.log.Loggable;
import ru.leo.springdemo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Loggable
    public UserDetails loadUserByUsername(String userName) {
        return repository.findByName(userName).get();
    }
}

