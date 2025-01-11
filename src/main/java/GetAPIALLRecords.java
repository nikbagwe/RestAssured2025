import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetAPIALLRecords {

    @Test
    public void getAPI(){

        RestAssured.baseURI = "https://gorest.co.in";

        RequestSpecification request =RestAssured.given();
        request.header("Authorization", "Bearer 1cef29185c0cae3732d425a781df80d644b47a9c222730426d8bb1b70c196b81");
        Response response = request.get("/public/v2/users");

        int statuscode = response.getStatusCode();
        System.out.println(statuscode);

        String repsonsedata = response.prettyPrint();
        System.out.println(repsonsedata);
    }
}
