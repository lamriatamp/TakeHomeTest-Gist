package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutUserTest extends BaseApiTest {
    @Test
    public void updateUser() {
        String requestBody = "{ \"name\": \"Lamria\", \"job\": \"Junior QA\" }";

        given()
                .header("x-api-key", apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("QA Tester"));
    }
}
