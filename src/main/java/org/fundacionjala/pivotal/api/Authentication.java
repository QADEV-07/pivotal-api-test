package org.fundacionjala.pivotal.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.fundacionjala.pivotal.util.Environment;

/**
 * @author Henrry Salinas.
 */
public final class Authentication {

    private static final String TOKEN_HEADER = "X-TrackerToken";

    private static final Environment ENVIRONMENT = Environment.getInstance();

    private static Authentication instance;

    private RequestSpecification requestSpecification;

    private Authentication() {
        initApi();
    }

    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }

    private void initApi() {
        requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(ENVIRONMENT.getUrlApi())
                .addHeader(TOKEN_HEADER, ENVIRONMENT.getApiToken())
                .build();
        if (!ENVIRONMENT.getProxyHost().isEmpty()) {
            requestSpecification.proxy(ENVIRONMENT.getProxy());
        }
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
