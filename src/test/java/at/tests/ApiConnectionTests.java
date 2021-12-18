package at.tests;

import at.study.redmine.api.client.RestApiClient;
import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.dto.users.UserDto;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import at.study.redmine.property.Property;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiConnectionTests {

    private final RequestSpecification ADMIN_REQUEST_SPECIFICATION = given()
            .baseUri(Property.getStringProperty("api.host"))
            .header("X-Redmine-API-Key", "55dfd83d5c925f999826c683114e589a4dd9f7e6")
            .log().all();

    @Test
    public void testSimpleRequest(){
        ADMIN_REQUEST_SPECIFICATION
                .request(Method.GET, "/users.json")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void createUserTest(){
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto(new User()));

        ADMIN_REQUEST_SPECIFICATION
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(userInfoDto))
                .request(Method.POST, "users.json")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void testGetUser(){
        User user = new User();
        List<Token> userToken = new ArrayList<>();
        userToken.add(new Token(user));
        user.setIsAdmin(true);
        user.setTokens(userToken);
        user.create();
        RestApiClient apiClient = new RestAssuredClient(user);
        apiClient.execute(new RestAssuredRequest(
                RestMethod.GET, "/users.json",null,null,null
        ));


    }
}
