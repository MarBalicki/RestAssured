import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetPostsTest {

    @Test
    public void getPosts() {
        given()
                .when().get("http://localhost:3000/posts")
                .then().log().body();

    }
}
