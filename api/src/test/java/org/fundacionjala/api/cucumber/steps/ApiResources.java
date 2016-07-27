package org.fundacionjala.api.cucumber.steps;

import java.util.Map;

import com.jayway.restassured.response.Response;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.fundacionjala.api.api.Mapper.addResponse;
import static org.fundacionjala.api.api.Mapper.mapEndpoint;
import static org.fundacionjala.api.api.RequestManager.deleteRequest;
import static org.fundacionjala.api.api.RequestManager.getRequest;
import static org.fundacionjala.api.api.RequestManager.postRequest;
import static org.fundacionjala.api.api.RequestManager.putRequest;
import static org.junit.Assert.assertEquals;

/**
 * @author Henrry Salinas.
 *
 * This class provide the basic step definitions to work with api rest requests
 */
public class ApiResources {

    private Response response;

    private Map<String, Object> parameters;

    private String endPoint;

    @Given("^I have the (.*) endpoint$")
    public void iHaveTheEndpoint(String endPoint) {
        this.endPoint = mapEndpoint(endPoint);
    }

    @Given("^I have the next parameters:$")
    public void iHaveTheNextParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @When("^I send a GET request to (.*) endpoint$")
    public void iSendAGETRequest(String endPoint) {
        response = getRequest(endPoint);
    }

    @When("^I sen(?:d|t) a POST request to (.*)$")
    public void iSendAPOSTRequestWith(String endPoint, Map<String, Object> parameters) {
        response = postRequest(this.endPoint=mapEndpoint(endPoint), parameters);
    }

    @When("^I send a PUT request to (.*?)$")
    public void iSendAPUTRequest(String endPoint, Map<String, Object> parameters) {
        response = putRequest(this.endPoint=mapEndpoint(endPoint), parameters);
    }

    @When("^I send a DELETE request$")
    public void iSendADELETERequest() {
        response = deleteRequest(endPoint);
    }

    @And("^stored as (.*)")
    public void storedAs(String key) {
        addResponse(key, response);
    }

    @Then("^I expect the status code (\\d+)$")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, response.statusCode());
    }

    public Response getResponse() {
        return response;
    }

    public String getEndPoint() {
        return endPoint;
    }

}
