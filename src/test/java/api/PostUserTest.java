package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostUserTest extends BaseApiTest {
    @Test
    public void createUser() {
        String requestBody = "{ \"name\": \"Lamria\", \"job\": \"QA Tester\" }";

        given()
                .header("x-api-key", apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Lamria M Tampubolon"))
                .body("job", equalTo("QA Tester"));
    }
}
