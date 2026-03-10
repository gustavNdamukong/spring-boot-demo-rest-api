package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Adding this @Component annotation makes this class a Bean, so it can be
// auto-injected (autowired) into classes that use it as a dependency.
// Note that any class that depends on it MUST also use the @Autowired
// annotation above the constructor method that injects it.
// Note that @Component & @Service do the same thing-@Service is more readable.
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // make this StudentService get its data from the data layer (repository)
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        // if the student is not already present, save them
        studentRepository.save(student);
    }


    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository
                .findById(studentId).orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));

        // update name
        if (name != null && name.length() > 0 &&
            !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        // update email
        if (email != null && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }



    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
