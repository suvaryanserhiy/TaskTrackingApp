# Task Tracking Application

A robust RESTful API for task management built with **Spring Boot 4.1**. This application provides complete CRUD operations for managing tasks with features like priority levels, status tracking, due dates, and validation.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Build the Application](#build-the-application)
  - [Run the Application](#run-the-application)
  - [Run with Docker UI](#run-with-docker-ui)
- [API Documentation](#api-documentation)
  - [Base URL](#base-url)
  - [Endpoints](#endpoints)
  - [Request & Response Examples](#request--response-examples)
- [Data Models](#data-models)
  - [Task Entity](#task-entity)
  - [Task Priority](#task-priority)
  - [Task Status](#task-status)
- [Validation Rules](#validation-rules)
- [Error Handling](#error-handling)
- [Configuration](#configuration)
- [Testing](#testing)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)
- [License](#license)

---

## Overview

The **Task Tracking Application** is a backend service designed to help individuals and teams manage their tasks efficiently. It offers a clean and intuitive REST API that supports:

- Creating new tasks with titles, descriptions, priorities, and due dates
- Updating existing tasks including status changes
- Listing all tasks sorted by creation date
- Deleting tasks when no longer needed

---

## Features

| Feature | Description |
|---------|-------------|
| **CRUD Operations** | Full Create, Read, Update, Delete functionality for tasks |
| **Priority Levels** | Support for HIGH, MEDIUM, and LOW priority classification |
| **Status Tracking** | Track tasks as OPEN or COMPLETE |
| **Due Date Management** | Optional due date with future/present validation |
| **Input Validation** | Comprehensive validation with meaningful error messages |
| **Global Exception Handling** | Centralized error handling for consistent API responses |
| **H2 Database** | Embedded database for easy development and testing |
| **Docker Support** | Docker Compose setup with a pre-built UI |

---

## Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 25 | Programming Language |
| **Spring Boot** | 4.1.0-M1 | Application Framework |
| **Spring Data JPA** | - | Data Persistence |
| **Spring Validation** | - | Input Validation |
| **H2 Database** | - | In-memory Database |
| **Maven** | - | Build & Dependency Management |
| **Docker** | - | Containerization |

---

## Architecture

The application follows a **layered architecture** pattern:

```
┌─────────────────────────────────────────────────────────────┐
│                      Controller Layer                       │
│              (REST API Endpoints & Request Handling)        │
├─────────────────────────────────────────────────────────────┤
│                       Service Layer                         │
│                   (Business Logic & Rules)                  │
├─────────────────────────────────────────────────────────────┤
│                      Repository Layer                       │
│                 (Data Access & Persistence)                 │
├─────────────────────────────────────────────────────────────┤
│                       Database (H2)                         │
│                    (Data Storage Layer)                     │
└─────────────────────────────────────────────────────────────┘
```

### Key Components

- **Controller** - Handles HTTP requests and responses
- **Service** - Contains business logic and orchestration
- **Repository** - Manages data persistence operations
- **Mapper** - Transforms between DTOs and entities
- **Exception Handler** - Provides centralized error handling

---

## Project Structure

```
src/
├── main/
│   ├── java/com/ssuvaryan/task/
│   │   ├── controller/
│   │   │   ├── GlobalExceptionHandler.java   # Centralized exception handling
│   │   │   └── TaskController.java           # REST API endpoints
│   │   ├── domain/
│   │   │   ├── dto/
│   │   │   │   ├── CreateTaskRequestDto.java # Create request payload
│   │   │   │   ├── ErrorDto.java             # Error response payload
│   │   │   │   ├── TaskDto.java              # Task response payload
│   │   │   │   └── UpdateTaskRequestDto.java # Update request payload
│   │   │   ├── entity/
│   │   │   │   ├── Task.java                 # Task JPA entity
│   │   │   │   ├── TaskPriority.java         # Priority enum
│   │   │   │   └── TaskStatus.java           # Status enum
│   │   │   ├── CreateTaskRequest.java        # Internal create request
│   │   │   └── UpdateTaskRequest.java        # Internal update request
│   │   ├── exception/
│   │   │   └── TaskNotFoundException.java    # Custom exception
│   │   ├── mapper/
│   │   │   ├── impl/
│   │   │   │   └── TaskMapperImpl.java       # Mapper implementation
│   │   │   └── TaskMapper.java               # Mapper interface
│   │   ├── repository/
│   │   │   └── TaskRepository.java           # JPA repository
│   │   ├── service/
│   │   │   ├── impl/
│   │   │   │   └── TaskServiceImpl.java      # Service implementation
│   │   │   └── TaskService.java              # Service interface
│   │   └── SsuvaryanTaskAppApplication.java  # Main application class
│   └── resources/
│       └── application.properties            # Application configuration
└── test/
    └── java/com/ssuvaryan/task/
        └── SsuvaryanTaskAppApplicationTests.java
```

---

## Prerequisites

Before running the application, ensure you have the following installed:

| Requirement | Minimum Version | Check Command |
|-------------|-----------------|---------------|
| **Java JDK** | 25 | `java -version` |
| **Maven** | 3.8+ | `mvn -version` |
| **Docker** (optional) | Latest | `docker --version` |

---

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd TaskTrackingApp/wip
```

### Build the Application

```bash
# Using Maven Wrapper (recommended)
./mvnw clean install

# Or using system Maven
mvn clean install
```

### Run the Application

```bash
# Using Maven Wrapper
./mvnw spring-boot:run

# Or using Java directly
java -jar target/task-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

### Run with Docker UI

The project includes a Docker Compose configuration that spins up a pre-built UI alongside your Spring Boot backend:

```bash
# Start the UI container
docker-compose up -d

# View logs
docker-compose logs -f

# Stop the containers
docker-compose down
```

- **Backend API**: http://localhost:8080
- **Frontend UI**: http://localhost:3000

---

## API Documentation

### Base URL

```
http://localhost:8080/api/v1/tasks
```

### Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `POST` | `/api/v1/tasks` | Create a new task | `CreateTaskRequestDto` | `201 Created` |
| `GET` | `/api/v1/tasks` | List all tasks | - | `200 OK` |
| `PUT` | `/api/v1/tasks/{taskId}` | Update a task | `UpdateTaskRequestDto` | `200 OK` |
| `DELETE` | `/api/v1/tasks/{taskId}` | Delete a task | - | `204 No Content` |

### Request & Response Examples

#### Create Task

**Request:**

```http
POST /api/v1/tasks
Content-Type: application/json
```

```json
{
  "title": "Complete project documentation",
  "description": "Write comprehensive README and API docs",
  "dueDate": "2026-03-15",
  "priority": "HIGH"
}
```

**Response:** `201 Created`

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Complete project documentation",
  "description": "Write comprehensive README and API docs",
  "dueDate": "2026-03-15",
  "priority": "HIGH",
  "status": "OPEN"
}
```

---

#### List All Tasks

**Request:**

```http
GET /api/v1/tasks
```

**Response:** `200 OK`

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "Complete project documentation",
    "description": "Write comprehensive README and API docs",
    "dueDate": "2026-03-15",
    "priority": "HIGH",
    "status": "OPEN"
  },
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "title": "Review code changes",
    "description": null,
    "dueDate": null,
    "priority": "MEDIUM",
    "status": "COMPLETE"
  }
]
```

---

#### Update Task

**Request:**

```http
PUT /api/v1/tasks/550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json
```

```json
{
  "title": "Complete project documentation",
  "description": "Updated description with more details",
  "dueDate": "2026-03-20",
  "priority": "MEDIUM",
  "status": "COMPLETE"
}
```

**Response:** `200 OK`

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Complete project documentation",
  "description": "Updated description with more details",
  "dueDate": "2026-03-20",
  "priority": "MEDIUM",
  "status": "COMPLETE"
}
```

---

#### Delete Task

**Request:**

```http
DELETE /api/v1/tasks/550e8400-e29b-41d4-a716-446655440000
```

**Response:** `204 No Content`

---

## Data Models

### Task Entity

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| `id` | `UUID` | Primary Key, Auto-generated | Unique identifier |
| `title` | `String` | Not null, Max 255 chars | Task title |
| `description` | `String` | Max 1000 chars, Nullable | Detailed description |
| `dueDate` | `LocalDate` | Nullable | Target completion date |
| `status` | `TaskStatus` | Not null | Current task status |
| `priority` | `TaskPriority` | Not null | Task priority level |
| `created` | `Instant` | Not null, Immutable | Creation timestamp |
| `updated` | `Instant` | Not null | Last modification timestamp |

### Task Priority

| Value | Description |
|-------|-------------|
| `HIGH` | Urgent tasks requiring immediate attention |
| `MEDIUM` | Normal priority tasks |
| `LOW` | Tasks that can be deferred |

### Task Status

| Value | Description |
|-------|-------------|
| `OPEN` | Task is pending/in progress |
| `COMPLETE` | Task has been finished |

---

## Validation Rules

The API enforces the following validation rules:

### Create Task Request

| Field | Rule | Error Message |
|-------|------|---------------|
| `title` | Required, 1-255 characters | "Title must be between 1 and 255 characters" |
| `description` | Optional, Max 1000 characters | "Description should be less than 1000 characters" |
| `dueDate` | Optional, Must be today or future | "Due date must be in the future" |
| `priority` | Required | "Task priority must be provided" |

### Update Task Request

| Field | Rule | Error Message |
|-------|------|---------------|
| `title` | Required, 1-255 characters | "Title must be between 1 and 255 characters" |
| `description` | Optional, Max 1000 characters | "Description should be less than 1000 characters" |
| `dueDate` | Optional, Must be today or future | "Due date must be in the future" |
| `priority` | Required | "Task priority must be provided" |
| `status` | Required | "Task status must be provided" |

---

## Error Handling

The API uses a global exception handler to provide consistent error responses.

### Error Response Format

```json
{
  "error": "Descriptive error message"
}
```

### HTTP Status Codes

| Status Code | Description | Scenario |
|-------------|-------------|----------|
| `200 OK` | Success | GET, PUT requests |
| `201 Created` | Resource created | POST requests |
| `204 No Content` | Successful deletion | DELETE requests |
| `400 Bad Request` | Invalid input | Validation errors, Task not found |
| `500 Internal Server Error` | Server error | Unexpected exceptions |

### Common Error Scenarios

| Error | HTTP Status | Response |
|-------|-------------|----------|
| Validation failure | `400` | `{"error": "Title must be between 1 and 255 characters"}` |
| Task not found | `400` | `{"error": "Task with ID '{id}' not found."}` |

---

## Configuration

### Application Properties

| Property | Default Value | Description |
|----------|---------------|-------------|
| `spring.application.name` | `Ssuvaryan Task App` | Application display name |

### Database Configuration

The application uses an **H2 in-memory database** by default. Data is stored temporarily and will be cleared when the application restarts.

To access the H2 Console (if enabled):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(empty)*

---

## Testing

Run the test suite using Maven:

```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report

# Run specific test class
./mvnw test -Dtest=SsuvaryanTaskAppApplicationTests
```

---

## Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit** your changes
   ```bash
   git commit -m "Add amazing feature"
   ```
4. **Push** to the branch
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open** a Pull Request

### Code Style Guidelines

- Follow standard Java naming conventions
- Write meaningful commit messages
- Add appropriate tests for new features
- Update documentation as needed

---

## Acknowledgments

This project was built following a **[Devtiro](https://www.youtube.com/@devtiro)** tutorial.

| Resource | Description | Link |
|----------|-------------|------|
| **Devtiro** | Tutorial creator and frontend UI provider | [YouTube](https://www.youtube.com/@devtiro) \| [GitHub](https://github.com/devtiro) |
| **Spring Boot** | Application framework powering the backend | [Website](https://spring.io/projects/spring-boot) |
| **H2 Database** | Embedded database for development | [Website](https://h2database.com) |

Special thanks to **[Devtiro](https://github.com/devtiro)** for:
- Creating the comprehensive Spring Boot tutorial that guided this project
- Providing the pre-built frontend UI container (`ghcr.io/devtiro/devtiro-build-task-app`)

---

## License

This project is open source and available under the [MIT License](LICENSE).

---

## Contact

**Developer:** Serhiy Suvaryan

---

<div align="center">

**Built with Spring Boot** | Frontend UI by [Devtiro](https://github.com/devtiro)

</div>
