package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUsersTest extends BaseApiTest {
    @Test
    public void getUsers() {
        given()
                .header("x-api-key", apiKey)
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data", not(empty()));
    }
}