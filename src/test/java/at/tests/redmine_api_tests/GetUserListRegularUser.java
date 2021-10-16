package at.tests.redmine_api_tests;

import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GetUserListRegularUser {

    private User firstUser;
    private User secondUser;
    private RestRequest request;
    private RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareFixture() {
        firstUser = new User();
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(firstUser));
        firstUser.setTokens(tokens);
        firstUser.create();

        secondUser = new User();
        secondUser.create();

        restAssuredClient = new RestAssuredClient(firstUser);

    }

    @Test
    public void GetUserListRegularUser(){
        request = new RestAssuredRequest(RestMethod.GET, "/users/"+ firstUser.getId() +".json", null,null,null);
        RestResponse response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200);
        UserInfoDto userInfoDto = response.getPayload(UserInfoDto.class);
        Assert.assertEquals(userInfoDto.getUser().getAdmin().booleanValue(), false);
        System.out.println(response.getHeaders().entrySet().toString());
        Assert.assertEquals(response.getHeaders().get("X-Redmine-API-key"), firstUser.getTokens().get(0).getValue());

    }
}
