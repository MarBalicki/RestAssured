import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JiraGetIssue {

    @Test
    public void getIssue() {
        given().auth().preemptive().basic("", "CyIbERsyP9QbV01hvYxe2520")
                .when().get("https://marcinbalicki.atlassian.net/rest/api/2/issue/TES-1")
                .then().log().all();
    }
}
