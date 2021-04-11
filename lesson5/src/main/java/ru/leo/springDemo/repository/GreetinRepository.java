package ru.leo.springDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leo.springDemo.controller.Greeting;

@Repository
public interface GreetinRepository extends JpaRepository<Greeting, Long> {
}
