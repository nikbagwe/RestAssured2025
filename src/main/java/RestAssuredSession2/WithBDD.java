package RestAssuredSession2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


/*
Continued from 1st Session
Notes
This is with BDD approach
We are calling hamcrest matchers library for assertions
.log().all() - can be used with given when then methods
this pattern of program matches with Builder Pattern
We can do all the assertion after .then() followed by assertThat()
We can use following function for assertion -> statusCode, contentType,  header and many more
body() to get the complete repsonse array/object
If its a array -> we can use $.size() -> $ means complete response 1

Different function for assertion in hamcrest matchers library are
equalTo - to check the status code or any other int , there are other methods for comparing different data types
is(notNullValue()) -> to check the given Json parameter does not have any null value
hasItem -> to check if the given text is there in response for specified parameter
 */
public class WithBDD {

    @Test
    public void getDataWithBDD() {
        given().log().all()
                .when().log().all() //to get all the logs , it can be used with given when and then
                .get("https://fakestoreapi.com/products")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("Connection", "keep-alive")
                .and()
                .body("$.size()", equalTo(200))
                .and()
                .body("id", is(notNullValue()))
                .and()
                .body("title", hasItem("Mens Cotton Jacket"));
    }

    //Session 2

    @Test

    public void goRestWithBDD()
    {
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                    .when().log().all()
                        .get("/public/v2/users/")
                            .then().log().all()
                                .assertThat()
                                    .statusCode(200)
                                        .and()
                                            .contentType(ContentType.JSON)
                                                .and()
                                                    .body("$.size()", equalTo(10))
                                                        .and()
                                                            .body("id",is(notNullValue()))
                                                                .and()
                                                                    .body("name",hasItem("apiname"));
        }

    @Test

    public void FakeStroesWithQueryParamsWithBDD(){

        Map<String, Integer> QueryMap = new HashMap<String, Integer>();
        QueryMap.put("limit",5);
        //https://fakestoreapi.com/products?limit=5
        given().log().all()
                .baseUri("https://fakestoreapi.com")
                    .when().log().all()
                        .queryParams(QueryMap)
                            .get("/products")
                                .then().log().all()
                                    .assertThat()
                                        .statusCode(200)
                                            .and()
                                                .body("$.size()",equalTo(5))
                                                    .and()
                                                        .contentType(ContentType.JSON);
    }

    @Test
    public void getAPIJSONPath(){


        Response response = given().log().all()
                .baseUri("https://gorest.co.in")
                    .and()
                        .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                            .when().log().all()
                                .get("/public/v2/users/");
        response.prettyPrint();
        JsonPath js =response.jsonPath();

        int id = js.getInt("[0].id");
        System.out.println("id = "+id);

        String name = js.getString("[0].name");
        System.out.println("Name is "+name);




    }

    @Test
    public void getDataExtractValueWithArrayResponse() {
        Response response = given().log().all()
                .when().log().all()
                .queryParams("limit",5)//to get all the logs , it can be used with given when and then
                .get("https://fakestoreapi.com/products");

        JsonPath js = response.jsonPath();

        List<Integer> idList =js.getList("id");
        List<String> titleList=js.getList("title");
        List<Float> rateList=js.getList("rating.rate");
        List<Integer> countList=js.getList("rating.count");

        for(int i=0;i<idList.size();i++)
        {
            int id=idList.get(i);
            String title=titleList.get(i);
            float rate=rateList.get(i);
            int count=countList.get(i);

            System.out.println("ID ="+id + " Title ="+title +" Rate ="+rate + " Count = "+count);
        }

    }
    @Test
    public void getDataExtractValueWithSingleJSONObjectResponse() {
        Response response = given().log().all()
                .baseUri("https://gorest.co.in")
                .and()
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                .when().log().all()
                .get("/public/v2/users/7631490");

        JsonPath js = response.jsonPath(); //converts response in JSON Response
        int id = js.getInt("id");
        String name=js.getString("name");

        System.out.println(id);
        System.out.println(name);

    }
    }

