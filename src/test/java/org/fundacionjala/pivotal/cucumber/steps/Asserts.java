package org.fundacionjala.pivotal.cucumber.steps;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
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
import static org.junit.Assert.assertTrue;

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

    @And("^I validate all setting projects$")
    public void iValidateAllSettingProjects() {
        Gson gson = new Gson();
        Map<ProjectSteps, Object> map = new HashMap<>();
        map = (Map<ProjectSteps, Object>) gson.fromJson(api.getResponse().print(), map.getClass());
        getAssertionMap(map).values()
                .forEach((steps) -> assertTrue("The fields is false ", steps));
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

    public Map<ProjectSteps, Boolean> getAssertionMap(Map<ProjectSteps, Object> values) {
        Map<ProjectSteps, Boolean> strategyMap = new HashMap<>();
        strategyMap.put(ID, validateSizeString(String.valueOf(values.get(ID.nameLowerCase()).toString()), 50));
        strategyMap.put(KIND, validateKind(values.get(KIND.nameLowerCase()).toString(), "project"));
        strategyMap.put(NAME, validateSizeString(values.get(NAME.nameLowerCase()).toString(), 50));
        strategyMap.put(VERSION, isAInt(values.get(VERSION.nameLowerCase()).toString()));
        strategyMap.put(ITERATION_LENGTH, isAInt(values.get(ITERATION_LENGTH.nameLowerCase()).toString()));
        strategyMap.put(WEEK_START_DAY, validateValueIntoList(Constants.nameDays, values.get(WEEK_START_DAY.nameLowerCase()).toString()));
        strategyMap.put(POINT_SCALE_IS_CUSTOM, isABoolean(values.get(POINT_SCALE_IS_CUSTOM.nameLowerCase()).toString()));
        strategyMap.put(BUGS_AND_CHORES_ARE_ESTIMATABLE, isABoolean(values.get(BUGS_AND_CHORES_ARE_ESTIMATABLE.nameLowerCase()).toString()));
        strategyMap.put(AUTOMATIC_PLANNING, isABoolean(values.get(AUTOMATIC_PLANNING.nameLowerCase()).toString()));
        strategyMap.put(ENABLE_TASKS, isABoolean(values.get(ENABLE_TASKS.nameLowerCase()).toString()));
        strategyMap.put(VELOCITY_AVERAGED_OVER, isAInt(values.get(VELOCITY_AVERAGED_OVER.nameLowerCase()).toString()));
        strategyMap.put(NUMBER_OF_DONE_ITERATIONS_TO_SHOW, isAInt(values.get(NUMBER_OF_DONE_ITERATIONS_TO_SHOW.nameLowerCase()).toString()));
        strategyMap.put(HAS_GOOGLE_DOMAIN, isABoolean(values.get(HAS_GOOGLE_DOMAIN.nameLowerCase()).toString()));
        strategyMap.put(ENABLE_INCOMING_EMAILS, isABoolean(values.get(ENABLE_INCOMING_EMAILS.nameLowerCase()).toString()));
        strategyMap.put(INITIAL_VELOCITY, isAInt(values.get(INITIAL_VELOCITY.nameLowerCase()).toString()));
        strategyMap.put(PUBLIC, isABoolean(values.get(PUBLIC.nameLowerCase()).toString()));
        strategyMap.put(ATOM_ENABLED, isABoolean(values.get(ATOM_ENABLED.nameLowerCase()).toString()));
        strategyMap.put(PROJECT_TYPE, projectType(values.get(PROJECT_TYPE.nameLowerCase()).toString()));
        strategyMap.put(START_TIME, validateStringDate(values.get(START_TIME.nameLowerCase()).toString()));
        strategyMap.put(CREATED_AT, validateStringDate(values.get(CREATED_AT.nameLowerCase()).toString()));
        strategyMap.put(UPDATED_AT, validateStringDate(values.get(UPDATED_AT.nameLowerCase()).toString()));
        strategyMap.put(ACCOUNT_ID, validateId(values.get(ACCOUNT_ID.nameLowerCase()).toString()));
        strategyMap.put(CURRENT_ITERATION_NUMBER, isAInt(values.get(CURRENT_ITERATION_NUMBER.nameLowerCase()).toString()));
        strategyMap.put(ENABLE_FOLLOWING, isABoolean(values.get(ENABLE_FOLLOWING.nameLowerCase()).toString()));
        return strategyMap;
    }

    private boolean projectType(String projectType) {
        return projectType.matches("demo|private|public|shared");
    }
}
