package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DeleteUserTest extends BaseApiTest {
    @Test
    public void deleteUser() {
        given()
                .header("x-api-key", apiKey)
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }
}
