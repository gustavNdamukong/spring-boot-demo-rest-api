# Spring Boot Student API

A RESTful API built with Spring Boot, demonstrating core backend development concepts using a simple Student management system as the domain model.

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Maven**

---

## Features

### REST API
- Full CRUD implementation via standard HTTP methods — `GET`, `POST`, `PUT`, `DELETE`
- Base path follows REST versioning convention: `/api/v1/student`
- URL path variables (`@PathVariable`) for resource-specific operations
- Optional query parameters (`@RequestParam`) for partial updates
- Automatic JSON serialisation/deserialisation via `@RestController` and `@RequestBody`

### Three-Tier Architecture
- **Controller layer** — handles HTTP routing and request/response mapping
- **Service layer** — enforces business logic and validation rules
- **Repository layer** — manages all database interaction via Spring Data JPA

### Dependency Injection & Spring IoC
- Constructor-based dependency injection using `@Autowired`
- Spring-managed Beans via stereotype annotations: `@RestController`, `@Service`, `@Repository`

### Data Persistence (JPA & Hibernate)
- Entity mapping with `@Entity`, `@Table`, `@Id`
- Database sequence-based ID auto-generation via `@SequenceGenerator` and `@GeneratedValue`
- `@Transient` field for computed values (age calculated from date of birth at runtime)
- Schema auto-management via `spring.jpa.hibernate.ddl-auto`
- Custom JPQL query using `@Query`

### Business Logic & Validation
- Duplicate email prevention on registration and update
- Existence check before delete operations
- Partial update pattern — only provided fields are updated
- `java.util.Optional` for safe null handling with `orElseThrow()`

### Transaction Management
- `@Transactional` on update operations with automatic dirty-checking and rollback on failure

### Application Configuration
- `@Configuration` class with `@Bean` definition
- `CommandLineRunner` for database seeding on application startup
- Centralised settings via `application.properties` (datasource, JPA, error messaging)

### Testing
- Integration test with `@SpringBootTest`
- Baseline `contextLoads()` smoke test

---

## Running the Project

**Prerequisites:** Java 17+, PostgreSQL running locally, a database named `student` created.

Update credentials in `src/main/resources/application.properties` if needed, then:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/api/v1/student`.

---

## Example Requests (HTTPie)

```bash
# Get all students
http GET http://localhost:8080/api/v1/student

# Add a student
http POST http://localhost:8080/api/v1/student name="Ada Lovelace" email="ada@example.com" dob="1815-12-10"

# Update a student
http PUT "http://localhost:8080/api/v1/student/1?name=Ada&email=ada.lovelace@example.com"

# Delete a student
http DELETE http://localhost:8080/api/v1/student/1
```
