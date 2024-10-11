
---

# XYZ Payment System

This is a Spring Boot application for managing student payments and validating student data. The application uses MySQL for persistent storage and mocks database services during testing.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Cloning the Repository](#cloning-the-repository)
    - [Database Setup](#database-setup)
    - [Environment Configuration](#environment-configuration)
    - [Building and Running the Application](#building-and-running-the-application)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features

- RESTful APIs for handling payment notifications and validating students.
- MySQL database integration.
- Uses Hibernate Envers for auditing.
- Mocks database services for tests, so no testing database setup is required.

## Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** (for database interactions)
- **MySQL**
- **Hibernate Envers** (for auditing)
- **JUnit 5** (for testing)
- **Mockito** (for mocking in tests)
- **Maven** (for build and dependency management)

## Prerequisites

Make sure you have the following installed:

- **Java 17**
- **Maven 3.8.x** or higher
- **MySQL 8.x**
- **Git** (for cloning the repository)

## Getting Started

### Cloning the Repository

To clone the project, run:

```bash
git clone github.com/tonymuga/xyz-school-payment-system/xyz-payment.git
cd xyz-payment
```

### Database Setup

1. **Install MySQL** if not installed already. You can follow the [MySQL installation guide](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/).

2. **Create the `xyz_payment` database**:

   Log into your MySQL instance and create the database:

   ```sql
   CREATE DATABASE xyz_payment;
   ```

3. **Optional**: Create a dedicated user for this database:

   ```sql
   CREATE USER 'xyz_user'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON xyz_payment.* TO 'xyz_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

### Environment Configuration

1. Rename the file `src/main/resources/application.yml.example` to `application.yml` and update it with your MySQL database configuration:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/xyz_payment
    username: xyz_user
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  audit:
    table-prefix: "xyz_"

logging:
  level:
    org.hibernate.SQL: debug
```

### Building and Running the Application

1. To build the application, run:

```bash
mvn clean install
```

2. To start the application, run:

```bash
mvn spring-boot:run
```

The application will start and be accessible at `http://localhost:8080`.

## Testing

We use Mockito to mock the database services in our tests, so no test database setup is needed.

To run the tests:

```bash
mvn test
```

## API Documentation

Here are the key endpoints exposed by the application:

1. **Validate Student** (POST `/api/student/validate`)
    - Request Body: `StudentRequest` JSON object
    - Response: A `ValidationResponse` object indicating if the student is enrolled or not.

2. **Payment Notification** (POST `/api/payment/notify`)
    - Request Body: `PaymentRequest` JSON object
    - Response: An `ApiResponse` object with a success or failure message.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

--- 
