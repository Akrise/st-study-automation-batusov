package at.tests;

import at.study.redmine.api.client.RestApiClient;
import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UsersListDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class ApiGetUsersListTest {

    private RestApiClient client;
    private RestRequest request;

    @BeforeMethod
    public  void prepareFixture(){
        User user = new User();
        user.setIsAdmin(true);
        user.setTokens(Collections.singletonList(new Token(user)));
        user.create();
        client = new RestAssuredClient(user);
        request = new RestAssuredRequest(RestMethod.GET,"/users.json", null, null, null);
    }

    @Test
    public void apiGetUsersListTest(){
        RestResponse response = client.execute(request);

        Assert.assertEquals(response.getStatusCode(), 200);

        UsersListDto responseData = response.getPayload(UsersListDto.class);

        Assert.assertEquals(responseData.getLimit().intValue(), 25);
    }
}
