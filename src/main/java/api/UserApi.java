package api;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class UserApi {
    private static final String BASE_URL = "http://localhost:8080/api/users";

    public static Response createUser(Map<String, Object> userData) {
        return RestAssured.given()
                .auth().basic("user", "password")
                .header("Content-Type", "application/json")
                .body(userData)
                .post(BASE_URL);
    }
    public static Response getUser(String userId) {
        return RestAssured.get(BASE_URL + "/" + userId);
    }
    public static Response updateUser(String userId, Map<String, Object> updatedData) {
        return RestAssured.given()
                .auth().basic("user", "password")
                .header("Content-Type", "application/json")
                .body(updatedData)
                .put(BASE_URL + "/" + userId);
    }
    public static Response deleteUser(String userId) {
        return RestAssured.delete(BASE_URL + "/" + userId);
    }
}