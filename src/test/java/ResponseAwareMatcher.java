import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class ResponseAwareMatcher {

    @Test
    public void getPost() {
        when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then().body("specificField",
                response -> Matchers.equalTo("10192020" + response.path("secretString")));

    }
}
