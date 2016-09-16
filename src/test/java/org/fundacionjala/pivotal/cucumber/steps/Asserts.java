package org.fundacionjala.pivotal.cucumber.steps;

import cucumber.api.java.en.Then;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.fundacionjala.pivotal.util.CommonMethods.getStringValueFromMapOfResponses;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Asserts {

    private static final int INDEX_1 = 0;

    private static final int INDEX_2 = 1;

    private ApiResources resources;

    public Asserts(ApiResources resources) {
        this.resources = resources;
    }

    @Then("^I expect the status code (\\d+)$")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, resources.getResponse().statusCode());
    }

    @Then("^The (.*?) field should be equals? to (.*)$")
    public void theProjectShouldBeUpdated(String fieldName, String expectedValue) {
        assertEquals(expectedValue, resources.getResponse().path(fieldName));
    }

    @Then("^I expect that \\[(.*)\\] be (.*)$")
    public void iExpectThatCommentNameBe(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertEquals(expectedResult, actualResult);
    }

    @Then("^I expect that \\[(.*)\\] not be (.*)$")
    public void iExpectThatCommentTextNotBeCommentTest(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertNotEquals(expectedResult, actualResult);
    }

    @Then("^I validate fields$")
    public void iValidateFields() {
        final String expected = "newStory";
        assertEquals(expected, resources.getResponse().jsonPath().get("name"));
    }

    @Then("^validate the schema:$")
    public void validateTheSchema(String schema)  {
        resources.getResponse().then().assertThat().body(matchesJsonSchema(schema));
    }

}
