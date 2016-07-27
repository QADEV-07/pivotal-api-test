package org.fundacionjala.api.util;

import java.util.ArrayList;
import java.util.Map;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.fundacionjala.api.api.RequestManager.deleteRequest;
import static org.fundacionjala.api.api.RequestManager.getRequest;
import static org.fundacionjala.api.util.Constants.PROJECTS_ENDPOINT;
import static org.fundacionjala.api.util.Constants.PROJECT_ID;

/**
 * Created by mijhailvillarroel.
 */
public final class CommonMethods {

    private CommonMethods() {
    }

    public static void deleteAllProjects() {
        ArrayList<Map<String, ?>> jsonAsArrayList = from(getRequest(PROJECTS_ENDPOINT).asString()).get("");
        if (jsonAsArrayList.size() > 0) {
            for (Map<String, ?> object : jsonAsArrayList) {
                deleteRequest(PROJECTS_ENDPOINT + object.get(PROJECT_ID).toString());
            }
        }
    }
}
