package at.study.redmine.api.rest_assured;

import at.study.redmine.api.client.RestApiClient;
import at.study.redmine.api.client.RestMethod;
import at.study.redmine.api.client.RestRequest;
import at.study.redmine.api.client.RestResponse;
import at.study.redmine.model.Token;
import at.study.redmine.model.User;
import at.study.redmine.property.Property;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredClient implements RestApiClient {

    protected RequestSpecification requestSpecification;

    /**
     * Initialize client without API-key
     */
    public RestAssuredClient() {
        this.requestSpecification = given()
                .baseUri(Property.getStringProperty("api.host"))
                .contentType(ContentType.JSON);
    }

    /**
     * Initialize client over user with API-key token
     *
     * @param user
     */
    public RestAssuredClient(User user) {
        this();
        String token = user.getTokens().stream()
                .filter(tkn -> tkn.getAction() == Token.TokenType.API)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User has no API-tokens"))
                .getValue();
        requestSpecification.header("X-Redmine-API-key", token);
    }

    /**
     * Get query result by RestAssured
     *
     * @param request Request to API
     * @return Response from API
     */
    @Override
    public RestResponse execute(RestRequest request) {
        RequestSpecification specification = given(requestSpecification)
                .queryParams(request.getQueryParameters())
                .headers(request.getHeaders());
        if (request.getBody() != null) {
            specification.body(request.getBody());
        }
        Response response = specification.log().all()
                .request(
                        toRestAssuredMethod(request.getMethod()),
                        request.getUri()
                );
        response.then().log().all();
        return new RestAssuredResponse(response);
    }

    private Method toRestAssuredMethod(RestMethod restMethod) {
        return Method.valueOf(restMethod.name());
    }

    //TODO закончил на 1:13
}
