package steps;
import api.UserApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.util.Map;

public class UserSteps {
    private Response response;
    private String userId;

    @When("I create a user {string} {string}")
    public void createUser(String firstName, String lastName) {
        System.out.println("Creating user: " + firstName + " " + lastName);
        response = UserApi.createUser(Map.of("firstName", firstName, "lastName", lastName, "dateOfBirth", "1990-01-01", "email", "test@example.com"));
        Assertions.assertEquals(201, response.statusCode(), "User creation failed!");
        userId = response.jsonPath().getString("id");
    }

    @When("I get the user by ID")
    public void getUser() {
        System.out.println("Fetching user with ID: " + userId);
        response = UserApi.getUser(userId);
        Assertions.assertEquals(200, response.statusCode(), "User retrieval failed!");
    }

    @When("I delete the user")
    public void deleteUser() {
        System.out.println("Deleting user with ID: " + userId);
        response = UserApi.deleteUser(userId);
        Assertions.assertEquals(204, response.statusCode(), "User deletion failed!");
    }

    @When("I create a user with missing {string}")
    public void createUserWithMissingField(String fieldName) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("firstName", "John");
        userData.put("lastName", "Doe");
        userData.put("email", "test@example.com");
    
        userData.remove(fieldName); // Simulate missing field
        response = UserApi.createUser(userData);
        Assertions.assertEquals(400, response.statusCode(), "Expected 400 for invalid input!");
    }
    
    @When("I fetch a user with an invalid ID")
    public void fetchInvalidUser() {
        response = UserApi.getUser("invalid-id");
        Assertions.assertEquals(404, response.statusCode(), "Expected 404 for non-existent user!");
    }
    
    @When("I fetch all users without authentication")
    public void fetchWithoutAuth() {
        response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .get(UserApi.BASE_URL); // Missing auth headers
        Assertions.assertEquals(401, response.statusCode(), "Expected 401 for unauthorized access!");
    }
     
}