package org.fundacionjala.pivotal.api;

import java.util.Map;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static com.jayway.restassured.RestAssured.given;

/**
 * The purpose of this class is to provide methods that manage the API REST
 * requests like GET, POST, PUT, and DELETE.
 *
 * @author Henrry Salinas.
 */
public class RequestManager {

    private static final Logger LOGGER = Logger.getLogger(RequestManager.class.getName());

    private static RequestSpecification REQUEST = Authentication.getInstance().getRequestSpecification();

    private RequestManager() {
    }

    public static Response getRequest(String endpoint) {
        LOGGER.info("GET endpoint is: " + endpoint);
        return given().spec(REQUEST)
                .when().get(endpoint);
    }

    public static Response postRequest(String endpoint, Map<String, Object> parameters) {
        LOGGER.info("POST endpoint is: " + endpoint);
        return given().spec(REQUEST).params(parameters)
                .when().post(endpoint);
    }

    public static Response putRequest(String endpoint, Map<String, Object> parameters) {
        LOGGER.info("PUT endpoint is: " + endpoint);
        return given().spec(REQUEST).params(parameters)
                .when().put(endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        LOGGER.info("DELETE endpoint is: " + endpoint);
        return given().spec(REQUEST).when().delete(endpoint);
    }
}

