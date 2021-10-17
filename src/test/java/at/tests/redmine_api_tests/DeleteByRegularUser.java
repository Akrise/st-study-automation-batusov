package at.tests.redmine_api_tests;

import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DeleteByRegularUser {

    private User firstUser;
    private User secondUser;
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
    public void DeleteByRegularUser() {
        //Отправить запрос DELETE на удаление пользователя из п.3, используя ключ из п.2. (удаление другого пользователя)
        RestRequest request = new RestAssuredRequest(RestMethod.DELETE, "/users/" + secondUser.getId() + ".json", null, null, null);
        RestResponse response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
        Assert.assertNotNull(secondUser.read().getId());

        //Отправить запрос DELETE на удаление пользователя из п.1, используя ключи из п.2 (удаление себя)
        request = new RestAssuredRequest(RestMethod.DELETE, "/users/" + firstUser.getId() + ".json", null, null, null);
        response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 403);
        Assert.assertNotNull(firstUser.read().getId());
    }
}
