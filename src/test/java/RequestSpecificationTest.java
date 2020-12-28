import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest {

    RequestSpecification specification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void setUp() {
        specification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:3000")
                .setBasePath("posts")
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        ResponseLoggingFilter resFilter = new ResponseLoggingFilter();
        RequestLoggingFilter reqFilter = new RequestLoggingFilter();
        RestAssured.filters(reqFilter, resFilter);
    }

    @Test
    public void getPosts() {
        given()
                .spec(specification)
                .when()
                .get()
                .then().spec(responseSpecification);
    }

    @Test
    public void getPost() {
        given()
                .spec(specification)
                .when()
                .get("1")
                .then().spec(responseSpecification);
    }


}
