package org.fundacionjala.pivotal.util;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.response.Response;

/**
 * This class will let us to use constants variables through the classes.
 *
 * @author Bruno Barrios
 */
public final class Constants {

    public static final int DELETE_STATUS_CODE = 204;

    public static final int SUCCESS_STATUS_CODE = 200;

    public static final String PROJECTS_ENDPOINT = "/projects/";

    public static final String PROJECT_ID = "id";

    public static final String CONFIG_PROPERTIES = "pivotal.properties";

    public static final String PROXY_PORT = "proxyPort";

    public static final String PROXY_HOST = "proxyHost";

    public static final String REMOTE_TEST_NAME = "remoteTestName";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String URL = "url";

    public static final String URL_API = "urlApi";

    public static final String API_TOKEN = "apiToken";

    public static final String REMOTE_ACCESS_KEY = "remoteAccessKey";

    public static final String REMOTE_USER = "remoteUser";

    public static final String LOCAL_BROWSER = "localBrowser";

    public static final String REMOTE_BROWSER = "remoteBrowser";

    public static final String REMOTE_BROWSER_VERSION = "remoteBrowserVersion";

    public static final String REMOTE_PLATFORM = "remotePlatform";


    public static final Map<String, Response> RESPONSE_VALUES = new HashMap<>();

    public static final String WORKSPACES_ENDPOINT = "/my/workspaces/";

    public static final String WORKSPACE_ID = "id";


    private Constants() {
    }
}
