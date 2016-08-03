package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.After;

import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.pivotal.util.Constants.PROJECT_ID;
import static org.fundacionjala.pivotal.util.Constants.RESPONSE_VALUES;

/**
 * This class will clean the enviroment after all Comments features have been executed.
 *
 * @author Bruno Barrios.
 */
public class ProjectHook {
    private static final String PROJECT_1 = "Project1";
    /**
     * This hook method will delete the project that is created into the Comments scenarios.
     */
    @After("@deleteProject")
    public void deleteAccount() {
        String id = RESPONSE_VALUES.get(PROJECT_1).jsonPath().get(PROJECT_ID).toString();
        deleteRequest(PROJECTS_ENDPOINT+id);
    }
}
