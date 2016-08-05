package org.fundacionjala.pivotal.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import org.fundacionjala.pivotal.ProjectSteps;
import org.fundacionjala.pivotal.util.Constants;

import static org.fundacionjala.pivotal.ProjectSteps.ACCOUNT_ID;
import static org.fundacionjala.pivotal.ProjectSteps.ATOM_ENABLED;
import static org.fundacionjala.pivotal.ProjectSteps.AUTOMATIC_PLANNING;
import static org.fundacionjala.pivotal.ProjectSteps.BUGS_AND_CHORES_ARE_ESTIMATABLE;
import static org.fundacionjala.pivotal.ProjectSteps.CREATED_AT;
import static org.fundacionjala.pivotal.ProjectSteps.CURRENT_ITERATION_NUMBER;
import static org.fundacionjala.pivotal.ProjectSteps.ENABLE_FOLLOWING;
import static org.fundacionjala.pivotal.ProjectSteps.ENABLE_INCOMING_EMAILS;
import static org.fundacionjala.pivotal.ProjectSteps.ENABLE_TASKS;
import static org.fundacionjala.pivotal.ProjectSteps.HAS_GOOGLE_DOMAIN;
import static org.fundacionjala.pivotal.ProjectSteps.ID;
import static org.fundacionjala.pivotal.ProjectSteps.INITIAL_VELOCITY;
import static org.fundacionjala.pivotal.ProjectSteps.ITERATION_LENGTH;
import static org.fundacionjala.pivotal.ProjectSteps.KIND;
import static org.fundacionjala.pivotal.ProjectSteps.NAME;
import static org.fundacionjala.pivotal.ProjectSteps.NUMBER_OF_DONE_ITERATIONS_TO_SHOW;
import static org.fundacionjala.pivotal.ProjectSteps.POINT_SCALE_IS_CUSTOM;
import static org.fundacionjala.pivotal.ProjectSteps.PROJECT_TYPE;
import static org.fundacionjala.pivotal.ProjectSteps.PUBLIC;
import static org.fundacionjala.pivotal.ProjectSteps.START_TIME;
import static org.fundacionjala.pivotal.ProjectSteps.UPDATED_AT;
import static org.fundacionjala.pivotal.ProjectSteps.VELOCITY_AVERAGED_OVER;
import static org.fundacionjala.pivotal.ProjectSteps.VERSION;
import static org.fundacionjala.pivotal.ProjectSteps.WEEK_START_DAY;
import static org.fundacionjala.pivotal.util.CommonMethods.getStringValueFromMapOfResponses;
import static org.fundacionjala.pivotal.util.CommonValidations.isABoolean;
import static org.fundacionjala.pivotal.util.CommonValidations.isAInt;
import static org.fundacionjala.pivotal.util.CommonValidations.validateId;
import static org.fundacionjala.pivotal.util.CommonValidations.validateKind;
import static org.fundacionjala.pivotal.util.CommonValidations.validateSizeString;
import static org.fundacionjala.pivotal.util.CommonValidations.validateStringDate;
import static org.fundacionjala.pivotal.util.CommonValidations.validateValueIntoList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Asserts {

    private static final int INDEX_1 = 0;

    private static final int INDEX_2 = 1;

    private ApiResources api;

    public Asserts(ApiResources api) {
        this.api = api;
    }

    @Then("^I expect the status code (\\d+)$")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, api.getResponse().statusCode());
    }

    @And("^The (.*?) field should be equals? to (.*)$")
    public void theProjectShouldBeUpdated(String fieldName, String expectedValue) {
        assertEquals(expectedValue, api.getResponse().path(fieldName));
    }

    @Then("^I expect the status code (\\d+)$")
    public void iExpectStatusCode(int statusCodeExpected) {
        assertEquals(statusCodeExpected, api.getResponse().statusCode());
    }

    @Then("^I expect that \\[(.*)\\] be (.*)$")
    public void iExpectThatCommentNameBe(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertEquals(expectedResult, actualResult);
    }

    @And("^I expect that \\[(.*)\\] not be (.*)$")
    public void iExpectThatCommentTextNotBeCommentTest(String expectedName, String expectedResult) {
        String[] value = expectedName.split("\\.");
        String actualResult = getStringValueFromMapOfResponses(value[INDEX_1], value[INDEX_2]);
        assertNotEquals(expectedResult, actualResult);
    }

    @Then("^I validate fields$")
    public void iValidateFields() {
        final String expected = "newStory";
        assertEquals(expected, api.getResponse().jsonPath().get("name"));
    }
}