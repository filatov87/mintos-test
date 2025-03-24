Feature: User API Testing

  Scenario: Create and retrieve a user
    When I create a user "John" "Doe"
    Then the status code should be 201
    When I get the user by ID
    Then the status code should be 200

  Scenario: Update a user
    When I create a user "Jane" "Smith"
    Then the status code should be 201
    When I update the user with new last name "Johnson"
    Then the status code should be 200

  Scenario: Delete a user
    When I create a user "Alice" "Brown"
    Then the status code should be 201
    When I delete the user
    Then the status code should be 204
