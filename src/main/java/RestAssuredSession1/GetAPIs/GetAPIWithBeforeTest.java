package RestAssuredSession1.GetAPIs;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAPIWithBeforeTest {

    RequestSpecification request;

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://gorest.co.in";

        request = RestAssured.given();
        request.header("Aut horization", "Bearer 1cef29185c0cae3732d425a781df80d644b47a9c222730426d8bb1b70c196b81");

    }

    @Test
    public void GetAllRecordsWithQueryParamsList() {

        request.queryParams("status","inactive");
        request.queryParams("name","Anjali");

        Response response =request.get("/public/v2/users");

        //Response

        //fetch status code
        int statuscode = response.statusCode();
        //assertion
        System.out.println(statuscode);
        Assert.assertEquals(statuscode, 200);

        //fetch status line
        String statusline = response.statusLine();
        System.out.println(statusline);

        //fetch the body
        String repsonsedata = response.prettyPrint();

        //respose header
        String header = response.header("Content-Type");
        System.out.println(header);

        //fetch all headers
        List<Header> headerList = response.headers().asList();
        System.out.println(headerList.size());

        //fetch all the header values
        // for (Header h : headerList) {
        //    System.out.println("Header Name: " +h.getName()+ "Header Value : "+ h.getValue());
        // }



    }

    @Test
    public void GetAllRecordsWithHashMapList() {

        //baseURL

        //Request

        //use hashmap for Query params
        Map<String, String> queryParamsMap = new HashMap<String, String>();
        queryParamsMap.put("name", "anjali");
        queryParamsMap.put("status", "inactive");


        request.header("Authorization", "Bearer 1cef29185c0cae3732d425a781df80d644b47a9c222730426d8bb1b70c196b81");
        request.queryParams(queryParamsMap);

        Response response =request.get("/public/v2/users");

        //Response

        //fetch status code
        int statuscode = response.statusCode();
        //assertion
        System.out.println(statuscode);
        Assert.assertEquals(statuscode, 200);

        //fetch status line
        String statusline = response.statusLine();
        System.out.println(statusline);

        //fetch the body
        String repsonsedata = response.prettyPrint();

        //respose header
        String header = response.header("Content-Type");
        System.out.println(header);

        //fetch all headers
        List<Header> headerList = response.headers().asList();
        System.out.println(headerList.size());

        //fetch all the header values
        // for (Header h : headerList) {
        //    System.out.println("Header Name: " +h.getName()+ "Header Value : "+ h.getValue());
        // }



    }

}
