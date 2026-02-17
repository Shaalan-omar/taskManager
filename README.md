# 📌 Task Manager API

A secure, production-ready **Task Management REST API** built with **Spring Boot**, **Spring Security**, **JWT**, **JPA**, and **MySQL**.

This project demonstrates:

* Layered architecture
* JWT-based authentication
* Role-based authorization (Keycloak compatible)
* Input validation
* Global exception handling
* Clean DTO ↔ Entity separation
* Production-ready security configuration

---

# 🚀 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **OAuth2 Resource Server (JWT)**
* **JPA / Hibernate**
* **MySQL**
* **Lombok**
* **Maven**

---

# 🏗 Architecture

The project follows a **Layered Architecture**:

```
Controller → Service → Repository → Database
```

### 📁 Packages

```
config/          → Security configuration
controller/      → REST endpoints
service/         → Business logic
repository/      → JPA repositories
model/           → JPA entities
dto/             → Request & Response DTOs
exception/       → Global exception handling
```

---

# 🔐 Security Overview

This project acts as an **OAuth2 Resource Server**.

### Authentication

* JWT-based authentication
* Bearer token required for protected endpoints

### Authorization Rules

```java
.requestMatchers("/public/**").permitAll()
.requestMatchers("/api/auth/**").permitAll()
.anyRequest().authenticated()
```

* `/public/**` → Open access
* `/api/auth/**` → Login / Register
* Everything else → Requires valid JWT

---

## 🔑 JWT Handling

Configured using:

```java
.oauth2ResourceServer(oauth -> oauth
    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
)
```

Custom converter:

```java
@Bean
public JwtAuthenticationConverter jwtAuthConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
    return converter;
}
```

Supports:

* Keycloak `realm_access.roles`
* Role-based authorization
* `@PreAuthorize("hasRole('ADMIN')")`

---

# 👤 User Features

* Create users
* Search users by email
* Email validation
* Role-based access control

### Email Validation

Uses regex validation:

```
^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$
```

Improved validation logic:

* Uses `matches()` instead of `find()`
* Validates actual Optional value
* Removes incorrect newline character

---

# 📝 Task Features

* Create tasks
* Assign tasks to users
* Due dates
* Status tracking
* Foreign key constraint handling

---

# ⚠️ Exception Handling

Global exception handler:

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ApiError> handleFallback(Exception ex)
```

Handles:

* Validation errors
* Data integrity violations
* Unexpected server errors

---

# 🗄 Database

Uses MySQL with JPA/Hibernate.

Example entities:

* `User`
* `Task`

Foreign key:

```
tasks.user_id → users.id
```

Cascade behavior:

```
ON DELETE CASCADE
ON UPDATE CASCADE
```

---

# ▶️ Running the Project

### 1️⃣ Clone

```bash
git clone https://github.com/Shaalan-omar/taskManager.git
```

### 2️⃣ Configure Database

Update `application.yml` or `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### 3️⃣ Run

```bash
./mvnw spring-boot:run
```

Or run from IDE.

---

# 🧪 Example Protected Request

```http
GET /api/tasks
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI...
```

If token is valid:

```
200 OK
```

If missing or invalid:

```
401 Unauthorized
```

---

# 📚 What This Project Demonstrates

* Deep understanding of Spring Security filter chain
* JWT validation lifecycle
* Custom authority conversion
* Proper use of Optional
* Correct regex validation
* Clean DTO mapping
* Separation of concerns
* Professional commit hygiene

---

# 🔥 Learning Milestones Achieved

* JPA persistence & dirty checking
* DTO vs Entity separation
* Security filter chain flow
* OAuth2 Resource Server configuration
* Role extraction from JWT
* CSRF vs stateless JWT APIs
* Proper Git workflow

---

# 👨‍💻 Author

Omar Shaalan
Backend Developer – Spring Boot & Security Focused
