# Product Management API

## 📌 Overview
This project is a Spring Boot REST API for managing products and items with secure authentication and role-based authorization.

It demonstrates clean architecture, secure API design, testing strategies, and containerization readiness.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Spring Security with JWT & Refresh Token
- Swagger/OpenAPI
- JUnit 5 & Mockito
- H2 Database (Testing)
- Docker & Docker Compose

---

## 🔐 Features

- JWT Authentication & Authorization
- Role-based access (ADMIN / USER)
- Product CRUD APIs
- Item management
- Pagination support
- Swagger API documentation
- Unit testing & Integration testing
- Docker containerization readiness

---

## 📂 Project Architecture

Controller → Service → Repository → Database
The project follows layered architecture with separation of concerns.

---

## 🔑 Security

The API is secured using JWT authentication.

- Users must login to obtain access token
- Protected endpoints require Bearer token
- Role-based authorization restricts access to ADMIN operations

---

## 📡 API Endpoints

### Auth
- POST /api/v1/auth/register
- POST /api/v1/auth/login
- POST /api/v1/auth/refresh

### Products
- GET /api/v1/products
- GET /api/v1/products/{id}
- POST /api/v1/products
- PUT /api/v1/products/{id}
- DELETE /api/v1/products/{id}

### Items
- POST /api/v1/products/{id}/items
- GET /api/v1/products/{id}/items

---

## 📄 Pagination Example

GET /api/v1/products?page=0&size=5

---

## ▶️ Running the Application

### 1️⃣ Clone repository
git clone <your-repo-url>

### 2️⃣ Configure database
Update application.properties with PostgreSQL credentials.

### 3️⃣ Run application
mvn spring-boot:run

---

## 🧪 Running Tests
mvn test
Tests use H2 in-memory database.

---

## 📖 Swagger Documentation
http://localhost:8080/swagger-ui/index.html

---

## 🐳 Docker

Dockerfile and docker-compose are included for containerized deployment readiness.

---
