package org.fundacionjala.pivotal.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.path.json.JsonPath.from;
/**
 * This class transforms a pseudo url like: projects/[Project1.id] to a valid url.
 *
 * @author Henrry Salinas.
 */
public final class Mapper {

    public static final Map<String, Response> RESPONSE_VALUES = new HashMap<>();

    private static final String REGEX_INSIDE_BRACKETS = "(?<=\\[)(.*?)(?=\\])";

    private static final String REGEX_DOT = "\\.";

    private static final String REGEX_BRACKETS = "[\\[\\]]";

    private Mapper() {
    }

    public static String mapEndpoint(String endPoint) {
        Matcher matches = Pattern.compile(REGEX_INSIDE_BRACKETS).matcher(endPoint);
        StringBuffer newEndPoint = new StringBuffer();

        while (matches.find()) {
            String[] parametersParts = matches.group().split(REGEX_DOT, 2);
            String key = parametersParts[0];
            String value = parametersParts[1];
            String replaceParameter = getField(RESPONSE_VALUES.get(key), value);
            matches.appendReplacement(newEndPoint, replaceParameter);
        }
        matches.appendTail(newEndPoint);
        return newEndPoint.toString().replaceAll(REGEX_BRACKETS, "");
    }

    public static void addResponse(String key, Response response) {
        RESPONSE_VALUES.put(key, response);
    }

    private static String getField(Response response, String parameter) {
        return from(response.asString()).get(parameter).toString();
    }

}
