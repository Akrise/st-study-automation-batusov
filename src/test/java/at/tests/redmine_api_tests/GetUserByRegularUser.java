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

public class GetUserByRegularUser {

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
    public void GetUserByRegularUser(){
        //Отправить запрос GET на получение пользователя из п.1, используя ключ API из п.2
        request = new RestAssuredRequest(RestMethod.GET, "/users/"+ firstUser.getId() +".json", null,null,null);
        RestResponse response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200);
        UserInfoDto userInfoDto = response.getPayload(UserInfoDto.class);
        Assert.assertEquals(userInfoDto.getUser().getAdmin().booleanValue(), false);
        Assert.assertEquals(userInfoDto.getUser().getApiKey(), firstUser.getTokens().get(0).getValue());
        //Отправить запрос GET на получения пользователя из п.3, используя ключ API из п.2
        request = new RestAssuredRequest(RestMethod.GET, "/users/"+ secondUser.getId() +".json", null,null,null);
        response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200);
        UserInfoDto secondUserInfoDto = response.getPayload(UserInfoDto.class);
        Assert.assertNull(secondUserInfoDto.getUser().getAdmin());
        Assert.assertNull(secondUserInfoDto.getUser().getApiKey());
    }
}
