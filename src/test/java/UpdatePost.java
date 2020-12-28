import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdatePost {

    @Test
    public void replacePost() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "tytul po aktualizacji");
        newPost.put("author", "Halinka");
        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().put("http://localhost:3000/posts/1")
                .then().log().all();
    }

    @Test
    public void replacePostObject() {
        Post post = new Post();
        post.setTitle("updated post");
        post.setAuthor("Marcin");
        //jeśli brak pola, które jest w obiekcie to będzie null
        //chyba że w klasie obiektu będzie @JsonInclude(Include.NON_NULL)
        given().log().all().contentType(ContentType.JSON).body(post)
                .when().put("http://localhost:3000/posts/1")
                .then().log().all();
    }

    @Test
    public void updatePostObject() {
        Post post = new Post();
        post.setTitle("updated title post");
        given().log().all().contentType(ContentType.JSON).body(post)
                .when().patch("http://localhost:3000/posts/1")
                .then().log().all();
    }
}
