package RestAssuredSession1.GetAPIs;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;


/*
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
    public void getDataWithBDD(){
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
                                                        .body("$.size()" ,equalTo(200))
                                                            .and()
                                                                .body("id", is(notNullValue()))
                                                                    .and()
                                                                        .body("title", hasItem("Mens Cotton Jacket"));
     }
}

