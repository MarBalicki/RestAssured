import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {
        given().log().all()
                .queryParam("author", "Marcinek")
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void filterPostsById() {
        given().log().all()
                .queryParam("id", 1, 3)
                .when().get("http://localhost:3000/posts")
                .then().log().all().statusCode(Matchers.greaterThanOrEqualTo(200));
    }

    @Test
    public void filterPostsByAuthorAndTitle() {
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Post z objektu");
        params.put("author", "Marcinek");
        given().log().all()
                .queryParams(params)
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }
}
