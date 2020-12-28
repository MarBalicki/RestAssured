import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddPostTest {

    @Test
    public void addPost() {
        String newPost = "{\n" +
                "    \"title\": \"Pierwszy post\",\n" +
                "    \"author\": \"Marcin\"\n" +
                "}";
        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostFromFile() {
        File newFile = new File("src/test/resources/post.json");
        given().log().all().contentType(ContentType.JSON).body(newFile)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostMap() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "tytul z mapy");
        newPost.put("author", "karolinka");
        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostObject() {
        Post post = new Post("Post z objektu", "Marcinek");
        given().log().all().contentType(ContentType.JSON).body(post)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }
}
