package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// A Controller is the entrypoint for your API. So all requests hit
// here first, therefore this is where your endpoints are defined.
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET REQUEST
    /*
    Make a GET request by simply visiting the endpoint in the browser:
    http://localhost:8080/api/v1/student
    */
    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    // POST REQUEST
    /*
        Make the POST request in the CLI via HTTPie like this:

            http POST http://localhost:8080/api/v1/student \
            Content-Type:application/json \
            name="Bilal" \
            email="bilal.ahmed@gmail.com" \
            dob="1995-12-17"
    */
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    // PUT REQUEST
    /* Make request in CLI using HTTPie like so:
        http PUT "http://localhost:8080/api/v1/student/20?name=Manon&email=manon.w@gmail.com"

    // This is how we pass dynamic vars to the request:
        @PutMapping(path = "{studentId}")

        Then retrieve it within the method using the @PathVariable().

        Note: In Spring Boot, the value inside @PathVariable must NOT
        contain braces ({...}). The braces are only used in the mapping
        annotation.
    */
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    // DELETE REQUEST
    /*
        Make DELETE request in CLI using HTTPie like so:
        http DELETE http://localhost:8080/api/v1/student/16

     This is how we pass dynamic vars to the request
    */
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
