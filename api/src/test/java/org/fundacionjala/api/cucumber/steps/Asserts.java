package org.fundacionjala.api.cucumber.steps;

import cucumber.api.java.en.And;

import static org.junit.Assert.assertEquals;

public class Asserts {

    private ApiResources api;

    public Asserts(ApiResources api) {
        this.api = api;
    }

    @And("^The (?:project|story) name should be equals? to (.*)$")
    public void theProjectShouldBeUpdated(String expectedName) {
        assertEquals(expectedName, api.getResponse().path("name"));
    }
}
