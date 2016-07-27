package org.fundacionjala.api.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.path.json.JsonPath.from;

public class Mapper {

    private static final String REGEX_INSIDE_BRACKETS = "(?<=\\[)(.*?)(?=\\])";

    private static final String REGEX_DOT = "\\.";

    public static final String REGEX_BRACKETS = "[\\[\\]]";

    public static final String REGEX_UNTIL_PROJECT = "^(\\/.*?\\/.*?\\/)";

    private static final Map<String, Response> RESPONSE_VALUES = new HashMap<>();

    private Mapper() {
    }

    public static String getField(Response response, String parameter) {
        return from(response.asString()).get(parameter).toString();
    }

    public static String mapEndpoint(String endPoint) {
        Matcher matches = Pattern.compile(REGEX_INSIDE_BRACKETS).matcher(endPoint);
        StringBuffer newEndPoint = new StringBuffer();
        String replaceParameter = "";
        while (matches.find()) {
            String[] parametersParts = matches.group().split(REGEX_DOT, 2);
            String key = parametersParts[0];
            String value = parametersParts[1];
            replaceParameter = Mapper.getField(RESPONSE_VALUES.get(key), value);
            matches.appendReplacement(newEndPoint, replaceParameter);
        }
        matches.appendTail(newEndPoint);
        return newEndPoint.toString().replaceAll(REGEX_BRACKETS, "");
    }

    public static String mapUrlToDeleteProject(String endPoint) {
        String projectUrlPart = "";
        Matcher matches = Pattern.compile(REGEX_UNTIL_PROJECT).matcher(endPoint);
        while (matches.find()) {
            projectUrlPart = matches.group();
        }
        return projectUrlPart;
    }

    public static void addResponse(String key, Response response) {
        RESPONSE_VALUES.put(key, response);
    }
}
