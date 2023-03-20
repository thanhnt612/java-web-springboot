package com.example.webapi.config;

import com.example.webapi.model.Student;
import com.example.webapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student john = new Student(
                    "John",
                    "john@gmail.com",
                    LocalDate.of(2000, DECEMBER, 12)
            );
            Student tim = new Student(
                    "Tim",
                    "tim@gmail.com",
                    LocalDate.of(1999, AUGUST, 5)
            );
            repository.saveAll(
                    List.of(john,tim)
            );
        };
    }
}
