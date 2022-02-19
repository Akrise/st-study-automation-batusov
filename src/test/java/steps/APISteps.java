package steps;

import at.study.redmine.api.client.RestApiClient;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.api.dto.users.UserDto;
import at.study.redmine.api.dto.users.UserInfoDto;
import at.study.redmine.api.rest_assured.RestAssuredClient;
import at.study.redmine.api.rest_assured.RestAssuredRequest;
import at.study.redmine.context.Context;
import at.study.redmine.model.User;
import com.google.gson.Gson;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import lombok.SneakyThrows;

import java.util.Map;

import static at.study.redmine.api.client.RestMethod.*;

public class APISteps {

    @И("Отправить (.*) запрос на создание пользователя (.*) от лица (.*)")
    public void createUserByPost(String requestName, String username, String executor) {
        User user = Context.getStash().get(username, User.class);
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto(user));
        RestApiClient restAssuredClient = new RestAssuredClient(Context.getStash().get(executor, User.class));
        RestRequest request = new RestAssuredRequest(POST, "/users.json", null, null, new Gson().toJson(userInfoDto));
        RestResponse response = restAssuredClient.execute(request);
        if (response.getPayload(UserInfoDto.class).getUser() != null) {
            user.setId(response.getPayload(UserInfoDto.class).getUser().getId());
            Context.getStash().put(username, user);
        }
        Context.getStash().put(requestName + " response", response);
    }

    @И("От лица (.*) отправить (.*) запрос с данными клиента из ответа на (.*) запрос, изменив данные:")
    public void updateUserByPut(String executor, String requestName, String responseName, Map<String, String> parameters) {
        RestAssuredClient client = new RestAssuredClient(Context.getStash().get(executor, User.class));
        RestResponse response = (RestResponse) Context.getStash().get(responseName + " response");
        UserInfoDto userInfoDto = response.getPayload(UserInfoDto.class);
        if (parameters.containsKey("status")) {
            userInfoDto.getUser().setStatus(Integer.parseInt(parameters.get("status")));
        }
        RestRequest request = new RestAssuredRequest(PUT, "users/" + userInfoDto.getUser().getId() + ".json", null, null, new Gson().toJson(userInfoDto));
        RestResponse newResponse = client.execute(request);
        Context.getStash().put(requestName + " response", newResponse);
    }

    @Если("Отправить (.*) запрос на получение пользователя (.*) от лица (.*)")
    public void readUserByGet(String requestName, String userName, String executor) {
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto((User) Context.getStash().get(userName)));
        RestApiClient restAssuredClient = new RestAssuredClient((User) Context.getStash().get(executor));
        RestRequest request = new RestAssuredRequest(GET, "/users/" + userInfoDto.getUser().getId() + ".json", null, null, null);
        RestResponse response = restAssuredClient.execute(request);
        Context.getStash().put(requestName + " response", response);
    }

    @SneakyThrows
    @Если("Отправить (.*) запрос на удаление пользователя (.*) от лица (.*)")
    public void deleteUserByDelete(String requestName, String username, String executor) {
        UserInfoDto userInfoDto = new UserInfoDto(new UserDto((User) Context.getStash().get(username)));
        RestApiClient restAssuredClient = new RestAssuredClient((User) Context.getStash().get(executor));
        RestRequest request = new RestAssuredRequest(DELETE, "/users/" + userInfoDto.getUser().getId() + ".json", null, null, null);
        RestResponse response = restAssuredClient.execute(request);
        Context.getStash().put(requestName + " response", response);
    }
}
