Feature: User API tests

  Scenario: Create User
    When I create a user "John" "Doe"
    Then the user should be created successfully

  Scenario: Get User by ID
    When I get the user by ID
    Then the user should be retrieved successfully

  Scenario: Invalid User Creation
    When I create a user with missing "firstName"
    Then the user creation should fail with a 400 status code

  Scenario: Update User
    When I update the user with new last name "Smith"
    Then the user should be updated successfully

  Scenario: Delete User
    When I delete the user
    Then the user should be deleted successfully

  Scenario: Fetch Non-Existent User
    When I fetch a user with an invalid ID
    Then the user retrieval should fail with a 404 status code
