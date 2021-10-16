package at.tests.redmine_api_tests;

import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UserDto;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.db.connection.DatabaseConnection;
import at.study.redmine.db.connection.PostgresConnection;
import at.study.redmine.db.requests.UserRequests;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import at.study.redmine.model.user.Status;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static at.study.redmine.api.client.RestMethod.*;
import static at.study.redmine.utils.StringUtils.*;

public class CRUDByAdmin {

    private User admin;
    private RestRequest request;
    private RestAssuredClient restAssuredClient;

    @BeforeMethod
    public void prepareFixture() {
        admin = new User();
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(admin));
        admin.setTokens(tokens);
        admin.setIsAdmin(true);
        admin.create();
        restAssuredClient = new RestAssuredClient(admin);
    }

    @Test
    public void CRUDByAdminTest() {
        //Отправить запрос POST на создание пользователя
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto(null, "BATAPITest" + randomEnglishString(5), false, 2, "BatName", "LastName", randomEnglishString(8) + "@bat.ru", "qi&*123qee", LocalDateTime.now(), LocalDateTime.now()));
        request = new RestAssuredRequest(POST, "/users.json", null, null, new Gson().toJson(userInfoDto));
        RestResponse response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 201);
        UserInfoDto userInfoDtoResponse = response.getPayload(UserInfoDto.class);
        UserInfoDto userInfoDtoFirstStep = response.getPayload(UserInfoDto.class);
        Integer userId = userInfoDtoResponse.getUser().getId();
        Assert.assertNotNull(userId);
        UserRequests userRequests = new UserRequests();
        Assert.assertEquals(userRequests.read(userId).getStatus(), Status.UNACCEPTED);

        //Отправить запрос POST на создание пользователя повторно с тем же телом запроса
        response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 422);
        userInfoDtoResponse = response.getPayload(UserInfoDto.class);
        List<String> errors = new LinkedList<>();
        errors.add("Email уже существует");
        errors.add("Пользователь уже существует");
        Assert.assertEquals(userInfoDtoResponse.getErrors(), errors);

        //Отправить запрос POST на создание пользователя повторно с тем же телом запроса, при этом изменив "email" на невалидный, а "password" - на строку из 4 символов
        userInfoDto.getUser().setPassword("1234");
        userInfoDto.getUser().setMail("google@gmail");
        request = new RestAssuredRequest(POST, "/users.json", null, null, new Gson().toJson(userInfoDto));
        response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 422);
        userInfoDtoResponse = response.getPayload(UserInfoDto.class);
        errors.clear();
        errors.add("Email имеет неверное значение");
        errors.add("Пользователь уже существует");
        errors.add("Пароль недостаточной длины (не может быть меньше 8 символа)");
        Assert.assertEquals(userInfoDtoResponse.getErrors(), errors);

        // Отправить запрос PUT на изменение пользователя. Использовать данные из ответа запроса, выполненного в шаге №1, но при этом изменить поле status = 1
        userInfoDtoFirstStep.getUser().setStatus(1);
        request = new RestAssuredRequest(PUT, "/users/" + userId + ".json", null, null, new Gson().toJson(userInfoDtoFirstStep));
        response = restAssuredClient.execute(request);
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertEquals(new UserRequests().read(userId).getStatus(), Status.ACTIVE);

        //Отправить запрос GET на получение пользователя
        request = new RestAssuredRequest(GET, "/users/" + userId + ".json", null, null, null);
        response = restAssuredClient.execute(request);
        UserInfoDto userInfoDtoGotten = response.getPayload(UserInfoDto.class);
        Assert.assertEquals(userInfoDtoGotten, userInfoDtoFirstStep);
        Assert.assertEquals(userInfoDtoGotten.getUser().getStatus().intValue(), Status.ACTIVE.statusCode);

        //Отправить запрос DELETE на удаление пользователя
        request = new RestAssuredRequest(DELETE, "/users/" + userId + ".json", null, null, null);
        try{
            response = restAssuredClient.execute(request);
        }catch (NoSuchElementException e){
            Assert.assertEquals(response.getStatusCode(), 204);
        }

        //Отправить запрос DELETE на удаление пользователя (повторно)
        request = new RestAssuredRequest(DELETE, "/users/" + userId + ".json", null, null, null);
        try{
            response = restAssuredClient.execute(request);
        }catch (NoSuchElementException e){
            Assert.assertEquals(response.getStatusCode(), 404);
        }

    }
}
