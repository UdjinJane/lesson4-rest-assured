package lesson4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class MySimpleJsonPostTest extends AbstractTest{
    @Test
            void post() throws IOException {

        String jsonBody = generateStringFromSource("src/main/resources/addtoshoppinglist.json");

        given()
                .spec(requestSpecification)
                .queryParam("hash", getHash())
                .when()
                .contentType("application/json")
                .body(jsonBody)
                .post(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list/items")
                .then()
                .statusCode(200)
                .body(containsString("id"));
    }

    public String generateStringFromSource (String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
