package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigReader;

public class BaseApiTest {
    protected String apiKey;

    @BeforeClass
    public void setUpApi() {
        RestAssured.baseURI = "https://reqres.in";
        apiKey = ConfigReader.get("REQRES_API_KEY");
    }
}