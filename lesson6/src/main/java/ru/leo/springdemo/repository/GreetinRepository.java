package ru.leo.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leo.springdemo.entity.Greeting;

@Repository
public interface GreetinRepository extends JpaRepository<Greeting, Long> {
}
