package lesson4;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lesson4.utils.PrintProperties;
import lesson4.utils.Writer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RunningTest extends AbstractTest {

    String globalID;

// Base URI вынесен в спецификацию реквеста.
// Простые тесты поиска.
    @Test
    void getSearchWorkingPositiveTest() {
        given()
                .spec(requestSpecification)
                .when()
                .get("recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void getSpecifyingRequestDataTest() {

        // Расширяю спецификацию ответа.

        // RestAssured.responseSpecification =
        //         responseSpecification
        //                 .expect()
        //                 .body("offset", is(0))
        //                 .body("number", is(10));
        // .body(containsString("84578389"));

        given()
                .spec(requestSpecification)
                .when()
                .get("recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .body(containsString("offset"))
                .body(containsString("number"));
         //       .log().all();


        // Расширяю спецификацию запроса.
        // excludeIngredients

        given()
                .spec(requestSpecification)
                .queryParam("excludeIngredients", "burger")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }



// complexCheck
// Расширяю спецификацию запроса.
@Test
    void getComplexRequestDataTest() {

        given()
                .spec(requestSpecification)
                .queryParam("includeIngredients", "eggs")
                .queryParam("excludeIngredients", "burger")
                .queryParam("cuisine", "Japanese")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);

// Sushi request Check

        given()

                .spec(requestSpecification)
                .queryParam("query", "sushi")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

//Блок POST запросов.
// Респонс в класс Response

@Test
void getWithExternalEndpointTest(){
    Response response = given().spec(requestSpecification)
            .when()
            .formParam("title","Sushi")
            .post("https://api.spoonacular.com/recipes/cuisine").prettyPeek()
            .then()
            .extract()
            .response()
            .body()
            .as(Response.class);
    assertThat(response.getCuisine(), containsString("Japanese"));
}

    @Test
    void PostSushiTest(){

        String response = given().spec(requestSpecification)
                .when()
                .formParam("title","Sushi")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .jsonPath()
                .get("offset");


// As json

        JsonPath  response2  = given().spec(requestSpecification)
                .when()
                .formParam("title","Sushi")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .extract()
                .jsonPath();


        assertThat(response2.get("confidence"), notNullValue());
        assertThat(response2.get("cuisine"), equalTo("Japanese"));
        System.out.println((String) response2.get("cuisine"));

    }

    @Test
    void AddGetDeleteToShoppingListTest() throws IOException {
        String jsonBody = generateStringFromSource("src/main/resources/addtoshoppinglist.json");
// Add c хардкодом закомментирован
        String id = given()

                .spec(requestSpecification)
                .queryParam("hash", getHash())
                .when()
//                .contentType("application/json")
//          .body(
//                  "{\n"
//                          + "\"item\": \"1 package baking powder\",\n"
//                          + "\"aisle\": \"Baking\",\n"
//                          + "\"parse\": true\n"
//                          + "}")
//

                .body(jsonBody).post(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list/items")
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        globalID = id;

// Пишем номер (id) айтема в файл

        Writer writer = new Writer();
        writer.write(globalID);
        System.out.println("id = " + globalID);

// Get
//         String getShoppingList =
                given()
                        .spec(requestSpecification)
                        .queryParam("hash", getHash())
                        .when()
                        .get(getBaseUrl() + "mealplanner/" + getUsername() + "/shopping-list")
                        .then()
                        .spec(responseSpecification)
                        .extract()
                        .jsonPath().toString();

 //       System.out.println("\n Shopping list: \n" + getShoppingList);

        // Delete
        String delResponse = given()
                .spec(requestSpecification)
                .queryParam("hash", getHash())
                .when()
                .delete(getBaseUrl()+ "mealplanner/"+ getUsername() +"/shopping-list/items/" + globalID)
                .then()
                .spec(responseSpecification)
                .body(containsString("success"))
                .extract()
                .jsonPath()
                .get("status")
                .toString();

        System.out.println("Delete response are: " + delResponse);
    }


    @Test
    void AllParametersToConsole() throws IOException {

        PrintProperties printProperties = new PrintProperties();
        printProperties.printALL();

    }

    public String generateStringFromSource (String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
