package org.fundacionjala.api.cucumber.steps;

import org.fundacionjala.api.api.Mapper;

import cucumber.api.java.After;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.fundacionjala.api.api.RequestManager.deleteRequest;
import static org.fundacionjala.api.util.Constants.DELETE_STATUS_CODE;
import static org.fundacionjala.api.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.api.util.Constants.SUCCESS_STATUS_CODE;

public class Hooks {

    ApiResources api;

    public Hooks(ApiResources api) {
        this.api = api;
    }

    @After("@project")
    public void afterProjectScenario() {
        deleteRequest(PROJECTS_ENDPOINT + from(api.getResponse().asString()).get("id").toString());
    }

    @After("@story")
    public void afterStoryScenario() {
        if (api.getResponse().statusCode() == SUCCESS_STATUS_CODE || api.getResponse().statusCode() == DELETE_STATUS_CODE) {
            deleteRequest(Mapper.mapUrlToDeleteProject(api.getEndPoint()));
        }
    }


}
