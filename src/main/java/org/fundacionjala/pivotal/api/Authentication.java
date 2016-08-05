package org.fundacionjala.pivotal.api;

import com.github.markusbernhardt.proxy.ProxySearch;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.fundacionjala.pivotal.util.Environment;

/**
 * @author Henrry Salinas.
 */
public class Authentication {

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
        if (ProxySearch.getDefaultProxySearch().getProxySelector() == null) {
            requestSpecification = new RequestSpecBuilder()
                    .setRelaxedHTTPSValidation()
                    .addHeader(TOKEN_HEADER, ENVIRONMENT.getApiToken())
                    .build();
        } else {
            requestSpecification = new RequestSpecBuilder()
                    .setRelaxedHTTPSValidation()
                    .setProxy(ENVIRONMENT.getProxy())
                    .addHeader(TOKEN_HEADER, ENVIRONMENT.getApiToken())
                    .build();
        }
        requestSpecification.baseUri(ENVIRONMENT.getUrlApi());
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
