package org.fundacionjala.pivotal.util;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.pivotal.util.Constants.PROJECT_ID;

import static org.fundacionjala.pivotal.util.Constants.RESPONSE_VALUES;

import static org.fundacionjala.pivotal.util.Constants.WORKSPACES_ENDPOINT;
import static org.fundacionjala.pivotal.util.Constants.WORKSPACE_ID;


/**
 * This class contains methods that will be used when they are required into the project.
 *
 * @author Mijhail Villarroel
 * @author Bruno Barrios
 */
public final class CommonMethods {

    private static final Logger LOGGER = Logger.getLogger (CommonMethods.class.getName ());

    private CommonMethods () {
    }

    /**
     * This method will be used to delete all project form Pivotal Tracker.
     */
    public static void deleteAllProjects () {
        ArrayList<Map<String, ?>> jsonAsArrayList = from (getRequest (PROJECTS_ENDPOINT).asString ()).get ("");
        if (jsonAsArrayList.size () > 0) {
            for (Map<String, ?> object : jsonAsArrayList) {
                deleteRequest (PROJECTS_ENDPOINT + object.get (PROJECT_ID).toString ());
            }
        }
    }

    /**
     * This method will be used to get an String value form the map of responses.
     *
     * @param key   is the name that makes reference to a specific response.
     * @param value will be the response
     * @return an String Value
     */
    public static String getStringValueFromMapOfResponses (String key, String value) {

        return RESPONSE_VALUES.get (key).jsonPath ().get (value);
    }

    /**
     * This method will be used to quit the current execution of project
     *
     * @param message
     */
    public static void quitProgram (String message) {
        LOGGER.error (message);
    }

    public static void deleteAllWorkspaces () {
        ArrayList<Map<String, ?>> jsonAsArrayList = from (getRequest (WORKSPACES_ENDPOINT).asString ()).get ("");
        if (jsonAsArrayList.size () > 0) {
            for (Map<String, ?> object : jsonAsArrayList) {
                deleteRequest (WORKSPACES_ENDPOINT + object.get (WORKSPACE_ID).toString ());
            }
        }
    }

}
