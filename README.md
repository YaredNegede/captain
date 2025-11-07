# Captain

A Spring Boot application that generates API from OpenAPI specification YAML files.

## Overview

This project demonstrates how to build a REST API using Spring Boot with code generation from an OpenAPI 3.0 specification. The application uses the OpenAPI Generator Maven plugin to automatically generate API interfaces and model classes from a YAML specification file.

## Features

- **OpenAPI-driven development**: API interfaces and models are automatically generated from the OpenAPI spec
- **Spring Boot 3.2**: Built on the latest stable Spring Boot version
- **RESTful APIs**: Two main API sets:
  - **Users API**: CRUD operations for user management
  - **Items API**: Operations for item management with category filtering
- **Swagger UI**: Interactive API documentation available at runtime
- **Bean Validation**: Request validation using Jakarta Bean Validation
- **In-memory storage**: Simple in-memory data storage for demonstration purposes

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- OpenAPI Generator 7.1.0
- Maven
- Springdoc OpenAPI (for Swagger UI)

## Project Structure

```
captain/
├── src/
│   ├── main/
│   │   ├── java/com/captain/
│   │   │   ├── CaptainApplication.java          # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java          # User API implementation
│   │   │   │   └── ItemController.java          # Item API implementation
│   │   │   └── service/
│   │   │       ├── UserService.java             # User business logic
│   │   │       └── ItemService.java             # Item business logic
│   │   └── resources/
│   │       ├── application.yaml                  # Application configuration
│   │       └── openapi/
│   │           └── api-spec.yaml                 # OpenAPI specification
│   └── test/
│       └── java/
└── target/
    └── generated-sources/openapi/                # Generated API code
        └── src/main/java/com/captain/
            ├── api/                               # Generated API interfaces
            └── model/                             # Generated model classes
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Build the Application

```bash
# Clean and package the application
mvn clean package

# Or just compile
mvn clean compile
```

The OpenAPI Generator will automatically run during the build process and generate:
- API interfaces in `target/generated-sources/openapi/src/main/java/com/captain/api/`
- Model classes in `target/generated-sources/openapi/src/main/java/com/captain/model/`

### Run the Application

```bash
# Run using Maven
mvn spring-boot:run

# Or run the packaged JAR
java -jar target/captain-1.0.0.jar
```

The application will start on port 8080.

### Access the API Documentation

Once the application is running, you can access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## API Endpoints

### Users API

- `GET /api/users` - Get all users
- `POST /api/users` - Create a new user
- `GET /api/users/{userId}` - Get user by ID
- `PUT /api/users/{userId}` - Update user
- `DELETE /api/users/{userId}` - Delete user

### Items API

- `GET /api/items` - Get all items (supports optional `category` query parameter)
- `POST /api/items` - Create a new item

## Example API Usage

### Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

### Get All Users

```bash
curl http://localhost:8080/api/users
```

### Create an Item

```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance laptop",
    "category": "Electronics",
    "price": 999.99
  }'
```

### Get Items by Category

```bash
curl http://localhost:8080/api/items?category=Electronics
```

## Modifying the API

To modify the API:

1. Edit the OpenAPI specification file at `src/main/resources/openapi/api-spec.yaml`
2. Run `mvn clean compile` to regenerate the API interfaces and models
3. Update your controller implementations if needed
4. Test your changes

## OpenAPI Generator Configuration

The OpenAPI Generator is configured in `pom.xml` with the following key settings:

- **Generator**: `spring` (generates Spring-based server code)
- **Interface Only**: `true` (generates only interfaces, not implementations)
- **Spring Boot 3**: `true` (uses Spring Boot 3 features)
- **Use Tags**: `true` (generates separate API interfaces per tag)

## License

This project is licensed under the terms included in the LICENSE file.