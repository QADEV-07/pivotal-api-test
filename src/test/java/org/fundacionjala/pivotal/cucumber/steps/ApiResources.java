package org.fundacionjala.pivotal.cucumber.steps;

import java.util.Map;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.fundacionjala.pivotal.api.Mapper.addResponse;
import static org.fundacionjala.pivotal.api.Mapper.mapEndpoint;
import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.api.RequestManager.postRequest;
import static org.fundacionjala.pivotal.api.RequestManager.putRequest;

/**
 * This class provide the basic step definitions to work with api rest requests
 *
 * @author Henrry Salinas.
 */
public class ApiResources {

    private Response response;

    private String endPoint;

    private Map<String, Object> MapParameter;

    @Given("^I have the (.*) endpoint$")
    public void iHaveTheEndpoint(String endPoint) {
        this.endPoint = mapEndpoint(endPoint);
    }

    @When("^I send a GET request to (.*) endpoint$")
    public void iSendAGETRequest(String endPoint) {
        response = getRequest(this.endPoint = mapEndpoint(endPoint));
    }

    @When("^I sen(?:d|t) a POST request to (.*)$")
    public void iSendAPOSTRequestWith(String endPoint, Map<String, Object> parameters) {
        MapParameter = parameters;
        response = postRequest(this.endPoint = mapEndpoint(endPoint), parameters);
    }

    @When("^I send a PUT request to (.*?)$")
    public void iSendAPUTRequest(String endPoint, Map<String, Object> parameters) {
        response = putRequest(this.endPoint = mapEndpoint(endPoint), parameters);
    }

    @When("^I send a DELETE request$")
    public void iSendADELETERequest() {
        response = deleteRequest(endPoint);
    }

    @And("^stored as (.*)")
    public void storedAs(String key) {
        addResponse(key, response);
    }


    @Given("^I send a DELETE request to (.*)$")
    public void iSendADELETERequestToEndpoint(String endpoint) {
        response = deleteRequest(this.endPoint = mapEndpoint(endPoint));
    }

    public Response getResponse() {
        return response;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
