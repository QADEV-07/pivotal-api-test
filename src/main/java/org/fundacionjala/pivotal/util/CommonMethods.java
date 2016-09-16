package org.fundacionjala.pivotal.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundacionjala.pivotal.api.Mapper;

import static io.restassured.path.json.JsonPath.from;
import static org.fundacionjala.pivotal.api.RequestManager.deleteRequest;
import static org.fundacionjala.pivotal.api.RequestManager.getRequest;
import static org.fundacionjala.pivotal.util.Constants.ATTRIBUTE_ID;
import static org.fundacionjala.pivotal.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.pivotal.util.Constants.WORKSPACES_ENDPOINT;


/**
 * This class contains methods that will be used when they are required into the project.
 *
 * @author Mijhail Villarroel
 * @author Bruno Barrios
 */
public final class CommonMethods {

    private static final Logger LOGGER = Logger.getLogger(CommonMethods.class.getName());

    private CommonMethods() {
    }

    /**
     * This method will be used to delete all project form Pivotal Tracker.
     */
    public static void deleteAllProjects() {
        List<Map<String, ?>> jsonAsArrayList = from(getRequest(PROJECTS_ENDPOINT).asString()).get("");
        for (Map<String, ?> object : jsonAsArrayList) {
            deleteRequest(PROJECTS_ENDPOINT + object.get(ATTRIBUTE_ID).toString());
        }
    }

    /**
     * This method delete all workspace from pivotal tracker.
     */
    public static void deleteAllWorkspaces() {
        List<Map<String, ?>> jsonAsArrayList = from(getRequest(WORKSPACES_ENDPOINT).asString()).get("");
        for (Map<String, ?> object : jsonAsArrayList) {
            deleteRequest(WORKSPACES_ENDPOINT + object.get(ATTRIBUTE_ID).toString());
        }
    }

    /**
     * This method will be used to get an String value form the map of responses.
     *
     * @param key   is the name that makes reference to a specific response.
     * @param value will be the response
     * @return an String Value
     */
    public static String getStringValueFromMapOfResponses(String key, String value) {
        return Mapper.getResponseValues().get(key).jsonPath().get(value);
    }

    /**
     * This method will be used to quit the current execution of project
     *
     * @param message
     */
    public static void quitProgram(String message) {
        LOGGER.error(message);
    }

}
