Feature: User API CRUD

Scenario: Create valid user
  Given valid user data
  When creating user
  Then status is 201

Scenario: Get existing user
  Given user exists
  When retrieving user by ID
  Then status is 200

Scenario: Invalid user creation
  When creating user with invalid data
  Then status is 400

Scenario: Update existing user
  Given user exists
  When updating user data
  Then status is 200

Scenario: Delete existing user
  Given user exists
  When deleting user
  Then status is 204

Scenario: Get non-existing user
  When retrieving non-existing user
  Then status is 404
