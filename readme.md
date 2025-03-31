# User API Automation Tests

This project contains a minimal test suite for validating CRUD operations of a User API using Java, Cucumber, and RestAssured.

## Overview

The tests cover the following scenarios:

- **Create User (Valid/Invalid):** Verify that a user is successfully created with valid data and that the API returns proper errors when data is invalid.
- **Get User:** Retrieve an existing user by their ID.
- **Update User:** Update user details and confirm the changes.
- **Delete User:** Delete an existing user and verify that the user is no longer retrievable.
- **Edge Cases:** Attempt operations on non-existing users to ensure appropriate error responses.

## Prerequisites

- **Java 11+** (or later)
- **Maven**
- **API Server or Stub:** The tests expect an API server running at `http://localhost:8080/api`.

> **Note:** If you do not have the live API available, you can use a mock server (e.g., WireMock) or a stub service to simulate the API responses as defined by the OpenAPI specification.

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/filatov87/mintos-test.git
    cd user-api-tests
    ```

2. Ensure the API is Running or Set Up a Stub:
    - **Live API:** Verify that your API server is accessible at `http://localhost:8080/api`.
    - **Mock Server:** Alternatively, configure a stub (e.g., using WireMock) to simulate the endpoints defined in the OpenAPI spec.

3. Build the project:
    ```bash
    mvn clean compile
    ```

## Running the Tests

To run all tests, simply execute:
```bash
mvn clean test
```

The tests will execute against the API endpoint specified in the code (`http://localhost:8080/api`). If the API server (or your mock server) is running correctly, all test scenarios should pass.

## Project Structure

```
user-api-tests/
├── pom.xml          <-- Maven project file with dependencies and build configuration
├── README.md        <-- This file
└── src/
     └── test/
          ├── java/
          │   ├── runners/
          │   │   └── TestRunner.java  <-- JUnit Runner for Cucumber tests
          │   └── steps/
          │       └── UserStepDefs.java  <-- Step definitions using RestAssured
          └── resources/
                └── features/
                     └── UserApi.feature  <-- Cucumber feature file with test scenarios
```
