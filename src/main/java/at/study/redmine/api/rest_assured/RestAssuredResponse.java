package at.study.redmine.api.rest_assured;

import at.study.redmine.api.client.RestResponse;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class RestAssuredResponse implements RestResponse {

    private int statusCode;
    private Map<String, String> headers;
    private String payload;

    public RestAssuredResponse(Response response) {
        this.statusCode = response.getStatusCode();
        this.headers = response.getHeaders().asList().stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue ));
        this.payload = response.getBody().asString();
    }

    @Override
    public <T> T getPayload(Class<T> clazz) {
        return new Gson().fromJson(payload, clazz);
    }
}
