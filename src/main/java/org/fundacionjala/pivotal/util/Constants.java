package org.fundacionjala.pivotal.util;

import java.util.Arrays;
import java.util.List;

/**
 * This class will let us to use constants variables through the classes.
 *
 * @author Bruno Barrios
 */
public final class Constants {

    public static final int SUCCESS_STATUS_CODE = 200;

    public static final String PROJECTS_ENDPOINT = "/projects/";

    public static final String WORKSPACES_ENDPOINT = "/my/workspaces/";

    public static final String ATTRIBUTE_ID = "id";

    public final static List<String> nameDays;
    static {
        nameDays = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    }

    private Constants() {
    }
}
