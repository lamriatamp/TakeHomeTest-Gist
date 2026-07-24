package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserByIdTest extends BaseApiTest {
    @Test
    public void getUserById() {
        given()
                .header("x-api-key", apiKey)
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2));
    }
}
