package ru.leo.springdemo.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.leo.springdemo.entity.Role;
import ru.leo.springdemo.entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> userList = Arrays.asList(
            new User("admin", new BCryptPasswordEncoder().encode("admin"), Collections.singletonList(Role.ADMIN), true, true, true, true),
            new User("guest", new BCryptPasswordEncoder().encode("guest"), Collections.singletonList(Role.GUEST), false, true, true, true),
            new User("leotiger", new BCryptPasswordEncoder().encode(""), Collections.singletonList(Role.ADMIN), true, true, true, true)
    );

    public Optional<User> findByName(String name) {
        return userList.stream()
                .filter(user -> user.getUsername().equals(name))
                .findFirst();
    }
}
