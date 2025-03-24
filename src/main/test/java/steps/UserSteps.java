package steps;
import api.UserApi;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class UserSteps {
    private Response response;
    private String userId;

    @When("I create a user {string} {string}")
    public void createUser(String firstName, String lastName) {
        response = UserApi.createUser(Map.of("firstName", firstName, "lastName", lastName, "dateOfBirth", "1990-01-01", "email", "test@example.com"));
        response.then().statusCode(201);
        userId = response.jsonPath().getString("id");
    }

    @Then("the user should be created successfully")
    public void userShouldBeCreatedSuccessfully() {
        response.then().body("id", notNullValue());
    }

    @When("I get the user by ID")
    public void getUser() {
        response = UserApi.getUser(userId);
        response.then().statusCode(200);
    }

    @Then("the user should be retrieved successfully")
    public void userShouldBeRetrievedSuccessfully() {
        response.then().body("id", notNullValue());
    }

    @When("I delete the user")
    public void deleteUser() {
        response = UserApi.deleteUser(userId);
        response.then().statusCode(204);
    }

    @Then("the user should be deleted successfully")
    public void userShouldBeDeletedSuccessfully() {
        response = UserApi.getUser(userId);
        response.then().statusCode(404);
    }

    @When("I create a user with missing {string}")
    public void createUserWithMissingField(String fieldName) {
        Map<String, Object> userData = Map.of(
            "firstName", "John",
            "lastName", "Doe",
            "email", "test@example.com"
        );

        // Simulate missing field
        if (fieldName.equals("firstName")) {
            userData = Map.of(
                "lastName", "Doe",
                "email", "test@example.com"
            );
        } else if (fieldName.equals("lastName")) {
            userData = Map.of(
                "firstName", "John",
                "email", "test@example.com"
            );
        } else if (fieldName.equals("email")) {
            userData = Map.of(
                "firstName", "John",
                "lastName", "Doe"
            );
        }

        response = UserApi.createUser(userData);
        response.then().statusCode(400);
    }

    @Then("the user creation should fail with a 400 status code")
    public void userCreationShouldFailWith400() {
        response.then().statusCode(400);
    }

    @When("I fetch a user with an invalid ID")
    public void fetchInvalidUser() {
        response = UserApi.getUser("invalid-id");
        response.then().statusCode(404);
    }

    @Then("the user retrieval should fail with a 404 status code")
    public void userRetrievalShouldFailWith404() {
        response.then().statusCode(404);
    }

    @When("I update the user with new last name {string}")
    public void updateUser(String newLastName) {
        Map<String, Object> updatedData = Map.of("lastName", newLastName);
        response = UserApi.updateUser(userId, updatedData);
        response.then().statusCode(200);
    }

    @Then("the user should be updated successfully")
    public void userShouldBeUpdatedSuccessfully() {
        response = UserApi.getUser(userId);
        response.then().body("lastName", equalTo("Smith"));
    }
}