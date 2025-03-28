package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UserStepDefs {

    private Response response;
    private static String userId;

    @Given("valid user data")
    public void validUserData() {
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @When("creating user")
    public void createUser() {
        response = given()
            .auth().basic("admin","admin")
            .contentType("application/json")
            .body("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"dateOfBirth\":\"1990-01-01\",\"personalIdDocument\":{\"documentId\":\"AB12345\",\"countryOfIssue\":\"LV\",\"validUntil\":\"2030-12-31\"}}")
            .post("/users");

        if (response.statusCode() == 201) {
            userId = response.jsonPath().getString("id");
        }
    }

    @Given("user exists")
    public void userExists() {
        if (userId == null) {
            validUserData();
            createUser();
        }
    }

    @When("retrieving user by ID")
    public void retrievingUserById() {
        response = given().auth().basic("admin","admin")
            .get("/users/" + userId);
    }

    @When("creating user with invalid data")
    public void createUserWithInvalidData() {
        response = given()
            .auth().basic("admin","admin")
            .contentType("application/json")
            .body("{}")
            .post("/users");
    }

    @When("updating user data")
    public void updateUserData() {
        response = given()
            .auth().basic("admin","admin")
            .contentType("application/json")
            .body("{\"firstName\":\"JohnUpdated\",\"lastName\":\"Doe\",\"email\":\"johnupdated@example.com\",\"dateOfBirth\":\"1990-01-01\",\"personalIdDocument\":{\"documentId\":\"AB12345\",\"countryOfIssue\":\"LV\",\"validUntil\":\"2030-12-31\"}}")
            .put("/users/" + userId);
    }

    @When("deleting user")
    public void deletingUser() {
        response = given().auth().basic("admin","admin")
            .delete("/users/" + userId);
    }

    @When("retrieving non-existing user")
    public void retrievingNonExistingUser() {
        response = given().auth().basic("admin","admin")
            .get("/users/nonexisting");
    }

    @Then("status is {int}")
    public void statusIs(int expected) {
        assertEquals(expected, response.statusCode());
    }
}
