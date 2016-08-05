package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.restassured.response.Response;

/**
 * This class transforms a pseudo url like: projects/[Project1.id] to a valid url.
 *
 * @author Henrry Salinas.
 */
public final class Mapper {

    private static final Map<String, Response> responseValues = new HashMap<>();

    private static final String BRACKET = "[";
    private static final String REGEX_KEY = "\\[(.*?)\\.";
    private static final String REGEX_VALUE = "\\.(.*?)\\]";
    private static final String REGEX_REPLACE = "\\[(.*?)\\]";
    
    private Mapper() {
    }

    /**
     * This method is to map a new endpoint
     * If the endpoint contains '[', the method take the key and value
     * to get from Map of responses and replace to format the new endpoint
     *
     * @param endPoint
     * @return
     */
    public static String mapEndpoint(String endPoint) {
        String newEndpoint = endPoint;
        if (endPoint.contains(BRACKET)) {
            Matcher mKey = Pattern.compile(REGEX_KEY).matcher(endPoint);
            Matcher mValue = Pattern.compile(REGEX_VALUE).matcher(endPoint);

            while (mKey.find() && mValue.find()) {
                final int groupRegex = 1;
                String key = mKey.group(groupRegex);
                String value = mValue.group(groupRegex);
                newEndpoint = newEndpoint.replaceFirst(REGEX_REPLACE, responseValues.get(key).jsonPath().get(value).toString());
            }
        }
        return newEndpoint;
    }

    /**
     * This method is used to save a new response
     * from the requests to API into a map
     *
     * @param key
     * @param response
     */
    public static void addResponse(String key, Response response) {
        responseValues.put(key, response);
    }

    public static Map<String, Response> getResponseValues() {
        return responseValues;
    }
}
