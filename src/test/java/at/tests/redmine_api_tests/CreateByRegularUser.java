package at.tests.redmine_api_tests;

import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UserDto;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.model.Email;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateByRegularUser {

    private User user;
    private RestRequest request;
    private RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareFixture() {
        user = new User();
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(user));
        user.setTokens(tokens);
        user.create();
        restAssuredClient = new RestAssuredClient(user);
    }


    @Test
    public void CreateByRegularUser() {
        User userToCreate = new User();
        user.getEmails().add(new Email(userToCreate));
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto(userToCreate));
        request = new RestAssuredRequest(RestMethod.POST, "/users.json", null,null, new Gson().toJson(userInfoDto));
        RestResponse response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
