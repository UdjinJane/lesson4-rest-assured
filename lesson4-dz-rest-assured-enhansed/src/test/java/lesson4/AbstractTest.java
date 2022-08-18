package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lesson4.utils.Inet;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;


public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static InputStream configFileID;
    private static String apiKey;
    private static String baseUrl;
    private static String contentType;

    private static Integer responsecode;
    private static Long sla;

    private static String hash;
    private static String username;
    private static String idItems;
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;


// Получаем переменные из файла свойств

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey =  prop.getProperty("apiKey");
        baseUrl= prop.getProperty("base_url");
        responsecode = Integer.valueOf(prop.getProperty("response_code"));
        sla =  Long.valueOf(prop.getProperty("sla"));
        contentType = prop.getProperty("contentType");
        hash = prop.getProperty("hash");
        username = prop.getProperty("username");

// формируем новый хэш
//        Inet inet = new Inet();
//        inet.sendPOST();
        configFileID = new FileInputStream("src/main/resources/id.txt");
        prop.load(configFileID);
        idItems = prop.getProperty("idItems");
//        username = prop.getProperty("username");

// Выносим все дубли

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(getSla()))
                //.expectHeader("Access-Control-Allow-Credentials", "true")
                //.log(LogDetail.ALL)
                .build();

// Базовый URI занесен в спецификацию
// GET


        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                //.log(LogDetail.ALL)
                .build();


        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;

    }


    @AfterEach
     void tearDownAfterClass() throws Exception {

        System.out.println("\n" + "@AfterAll : Tests OK!" + "\n");
    }



    public static String getApiKey() {
        return apiKey;
    }
    public static String getBaseUrl() {
        return baseUrl;
    }

    public static Integer getResponseCode() { return  responsecode; }
    public static Long getSla() { return sla; }
    public static String getContentType () {return contentType;}
    public static String getHash() {return hash;}
    public static String getIdItems() {return idItems;}
    public static String getUsername() {return username;}

    public RequestSpecification getRequestSpecification(){
        return requestSpecification;
    }
    public ResponseSpecification getResponseSpecification(){
        return responseSpecification;
    }

}
