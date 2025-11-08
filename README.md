# Captain

A Spring Boot application that generates API from OpenAPI specification YAML files.

## Overview
 

## Features
 
## Technology Stack

- Java 17
- Spring Boot 3.2.0
- OpenAPI Generator 7.1.0
- Maven
- Springdoc OpenAPI (for Swagger UI)

## Project Structure
 
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
 

### Items API
 

## Example API Usage
 

### Create an Item
  
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