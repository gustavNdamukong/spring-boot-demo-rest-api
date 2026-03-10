package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This class represents the data layer of the application.
// It sits between the service and the DB. So the three tier application
// design goes like this: Controller - Service - Repository - Entity - DB
// Remember; the StudentService is a bean (@Component/@Service) that gets
// autowired (injected) into the Controller.
// Then the Service itself will use this repository (via @Autowire), since
// this repository's job is to provide the data access.
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
    // The Student below is the class, & it works because we marked the
    // Student class with a @Entity annotation. This is JPQL & not SQL
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
