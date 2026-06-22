# Social Media Platform API

A backend REST API for a professional social media platform built with Spring Boot. The platform allows registered authors to publish posts. An author must exist in the system before a post can be created.

---

## Tech Stack

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok

---

## Project Structure

```
src/main/java/com/example/SocailMedia/
├── controller/
│   ├── AuthorController.java       ← handles all /authors HTTP requests
│   └── PostController.java         ← handles all /posts HTTP requests
├── service/
│   ├── AuthorService.java          ← business logic for authors
│   └── PostService.java            ← business logic for posts
├── repository/
│   ├── AuthorRepository.java       ← database queries for authors
│   └── PostRepository.java         ← database queries for posts
├── model/
│   ├── Author.java                 ← Author entity (maps to database table)
│   └── Post.java                   ← Post entity (maps to database table)
└── dto/
    ├── CreateAuthorRequest.java    ← input when creating an author
    ├── UpdateAuthorRequest.java    ← input when updating an author
    ├── AuthorResponse.java         ← output returned for author operations
    ├── CreatePostRequest.java      ← input when creating a post
    ├── UpdatePostRequest.java      ← input when updating a post
    └── PostResponse.java           ← output returned for post operations
```

---

## Database Setup

Make sure PostgreSQL is running on your machine, then create the database:

```sql
CREATE DATABASE media;
```

Update `src/main/resources/application.properties` with your own credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/media
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> `ddl-auto=update` means Spring Boot will automatically create and update the tables for you. No SQL scripts needed.

---

## How to Run

```bash
./mvnw spring-boot:run
```

The API will start at: `http://localhost:8080`

---

## Entities

### Author

| Field       | Type          | Notes                  |
|-------------|---------------|------------------------|
| id          | Long          | Auto-generated         |
| fullName    | String        |                        |
| username    | String        | Must be unique         |
| email       | String        | Must be unique         |
| bio         | String        | Up to 1000 characters  |
| createdAt   | LocalDateTime | Set automatically      |

### Post

| Field       | Type          | Notes                        |
|-------------|---------------|------------------------------|
| id          | Long          | Auto-generated               |
| title       | String        |                              |
| content     | String        | Up to 3000 characters        |
| visibility  | String        | `PUBLIC` or `PRIVATE`        |
| createdBy   | Author        | Many posts belong to one author |
| createdAt   | LocalDateTime | Set automatically            |

---

## Validation Rules

### Author
| Field    | Rule                                  |
|----------|---------------------------------------|
| fullName | At least 5 characters                 |
| username | Cannot be blank, must be unique       |
| email    | Must be a valid email format          |
| bio      | No length limit (can exceed 200 chars)|

### Post
| Field      | Rule                              |
|------------|-----------------------------------|
| title      | Between 10 and 100 characters     |
| content    | Between 50 and 2000 characters    |
| visibility | Must not be null                  |

---

## API Endpoints

### Author Endpoints

| Method | Endpoint                        | Description                        |
|--------|---------------------------------|------------------------------------|
| POST   | `/authors`                      | Create a new author                |
| GET    | `/authors`                      | Get all authors                    |
| GET    | `/authors/{id}`                 | Get author by ID                   |
| GET    | `/authors/username/{username}`  | Get author by username             |
| GET    | `/authors/email/{email}`        | Get author by email                |
| GET    | `/authors/period?start=&end=`   | Get authors created in a period    |
| PUT    | `/authors/{id}`                 | Update author profile              |
| DELETE | `/authors/{id}`                 | Delete author by ID                |

### Post Endpoints

| Method | Endpoint                        | Description                        |
|--------|---------------------------------|------------------------------------|
| POST   | `/posts`                        | Create a new post                  |
| GET    | `/posts`                        | Get all posts                      |
| GET    | `/posts/{id}`                   | Get post by ID                     |
| GET    | `/posts/author/{authorId}`      | Get all posts by a specific author |
| PUT    | `/posts/{id}`                   | Update post (title and content)    |
| DELETE | `/posts/{id}`                   | Delete post by ID                  |
| DELETE | `/posts/author/{authorId}`      | Delete all posts by an author      |
| DELETE | `/posts/period?start=&end=`     | Delete posts created in a period   |

---

## Request & Response Examples

### Create Author
**POST** `/authors`
```json
{
  "fullName": "Karlie Moana",
  "username": "karlie_m",
  "email": "karlie@email.com"
}
```
**Response:**
```json
{
  "message": "Author created successfully",
  "fullName": "Karlie Moana",
  "username": "karlie_m",
  "email": "karlie@email.com",
  "createdAt": "2025-06-01T10:00:00"
}
```

---

### Update Author
**PUT** `/authors/1`
```json
{
  "fullName": "Karlie Updated",
  "username": "karlie_updated",
  "email": "karlie_new@email.com",
  "bio": "Updated bio — backend developer passionate about clean code."
}
```

---

### Create Post
**POST** `/posts`
```json
{
  "title": "My First Spring Boot Post",
  "content": "This is my first post using Spring Boot. I am learning how to build REST APIs with proper layered architecture and best practices.",
  "authorId": 1,
  "visibility": "PUBLIC"
}
```
**Response:**
```json
{
  "message": "Post created successfully",
  "title": "My First Spring Boot Post",
  "content": "This is my first post using Spring Boot...",
  "visibility": "PUBLIC",
  "createdAt": "2025-06-01T10:05:00",
  "createdBy": "Karlie Moana"
}
```

---

### Update Post
**PUT** `/posts/1`
```json
{
  "title": "Updated Spring Boot Post Title",
  "content": "This content has been updated. Spring Boot makes it very easy to build and maintain REST APIs with a clean layered architecture."
}
```

---

### Get Authors by Period
**GET** `/authors/period?start=2025-01-01T00:00:00&end=2025-12-31T23:59:59`

---

### Delete Posts by Period
**DELETE** `/posts/period?start=2025-01-01T00:00:00&end=2025-12-31T23:59:59`

---

## Layered Architecture

```
HTTP Request
     ↓
Controller       ← receives the request, calls the service
     ↓
Service          ← contains all the business logic
     ↓
Repository       ← talks to the database
     ↓
Database (PostgreSQL)
```

> Entities are never exposed directly through the API. Every endpoint receives a **Request DTO** and returns a **Response DTO**.
