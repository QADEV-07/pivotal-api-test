package org.fundacionjala.api.api;

import java.util.Map;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author Henrry Salinas.
 *
 * The purpose of this class is to provide methods that manage the API REST
 * requests like GET, POST, PUT, and DELETE.
 */
public class RequestManager {

    private static RequestSpecification REQUEST = Authentication.getInstance().getRequestSpecification();

    private RequestManager() {
    }

    public static Response getRequest(String endpoint) {
        return given().spec(REQUEST)
                .when().get(endpoint);
    }

    public static Response postRequest(String endpoint, Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().post(endpoint);
    }

    public static Response putRequest(String endpoint, Map<String, Object> parameters) {
        return given().spec(REQUEST).params(parameters)
                .when().put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        return given().spec(REQUEST).when().delete(endpoint);
    }
}

