import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class VerifyResponse {

    @Test
    public void getPost() {
        String expected = "{\n" +
                "  \"title\": \"updated title post\",\n" +
                "  \"author\": null,\n" +
                "  \"id\": 1\n" +
                "}";
        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(equalTo(expected));
    }

    @Test
    public void getPostContains() {
        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.containsStringIgnoringCase("marcin"));
    }

    @Test
    public void checkSpecificField() {
//        given().log().all().
        when()
                .get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all()
                .assertThat().body("title", containsString("updated"))
                .and()
                .assertThat().body("author", equalTo("Marcin"));
    }

    @Test
    public void getPostObject() {
        Post newPost = given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body("title", containsString("updated"))
                .and().body("author", equalTo("Marcin"))
                .extract().body().as(Post.class);
        Assert.assertEquals(newPost.getAuthor(), "Marcin");
        Assert.assertEquals(newPost.getTitle(), "updated post");
        Assert.assertEquals(newPost.getId(), java.util.Optional.of(1L).get());
    }


    @Test
    public void addedPostObjectWithSameTitleAndAuthorShouldBeEqual() {
        Post post = new Post("Post z objektu", "Marcinek");
        Post createdPost = given().log().all().contentType(ContentType.JSON).body(post)
                .when().post("http://localhost:3000/posts")
                .then().log().all().extract().body().as(Post.class);
        Assert.assertEquals(post, createdPost);
    }
}
