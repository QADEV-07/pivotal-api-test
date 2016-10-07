package org.fundacionjala.pivotal.cucumber.hooks;

import cucumber.api.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import org.fundacionjala.pivotal.util.Environment;

import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllProjects;
import static org.fundacionjala.pivotal.util.CommonMethods.deleteAllWorkspaces;
import static org.fundacionjala.pivotal.util.CommonMethods.quitProgram;
import static org.fundacionjala.pivotal.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.pivotal.util.Constants.SUCCESS_STATUS_CODE;

/**
 * This class stores the global hooks methods required to run the test
 *
 * @author Henrry Salinas.
 */
public class GlobalHooks {

    private static final String LOG4J_PROPERTIES = "src/main/resources/log4j.properties";

    private static final String PROPERTIES_FILE_UNFILLED = "Error reading the properties file, one of the next properties is missing: email, org token or password";

    private static final String API_CREDENTIALS_INCORRECT = "The org rest credentials is not correct";

    private static boolean beforeAllFlag;

    @Before
    public void beforeAll() {
        if (!beforeAllFlag) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    deleteAllProjects();
                    deleteAllWorkspaces();
                }
            });
            if (StringUtils.isEmpty(Environment.getInstance().getApiToken())) {
                quitProgram(PROPERTIES_FILE_UNFILLED);
            } else if (getRequest(PROJECTS_ENDPOINT).statusCode() != SUCCESS_STATUS_CODE) {
                quitProgram(API_CREDENTIALS_INCORRECT);
            }
            beforeAllFlag = true;
        }
        PropertyConfigurator.configure(LOG4J_PROPERTIES);
    }

    @Before("@CleanEnvironment")
    public void cleanEnvironment() {
        deleteAllProjects();
        deleteAllWorkspaces();
    }
}
