package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    // This @Bean annotation is very important. It allows Spring access
    // to the DB via the repository (in this case StudentRepository)
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                 "Mariam",
                 "mariam.king@gmail.com",
                 LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student gus = new Student(
                    "Gustav",
                    "gustavo@gmail.com",
                    LocalDate.of(2004, Month.MARCH, 25)
            );

            repository.saveAll(
                    List.of(mariam, gus)
            );
        };
    }
}
