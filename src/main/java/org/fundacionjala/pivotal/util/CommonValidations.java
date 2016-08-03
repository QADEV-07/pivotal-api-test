package org.fundacionjala.pivotal.util;

import java.util.List;

/**
 * Created by mijhailvillarroel on 7/22/2016.
 */
public final class CommonValidations {
    private CommonValidations() {

    }

    public static boolean validateId(String id) {
        return id.matches("[0-9]{7}");
    }

    public static boolean validateSizeString(String word, int size) {
        return word.length() <= size;
    }

    public static boolean validateValueIntoList(List<String> valuesList, String valueSearch) {
        return valuesList.contains(valueSearch);
    }

    public static boolean validateStringDate(String date) {
        return date.matches("[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T(0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9]):(0[0-9]|[1-5][0-9])Z");
    }

    public static boolean isABoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    public static boolean isAInt(String value) {
        try {

            Integer.parseInt((int) Double.parseDouble(value) + "");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean validateKind(String valueSearch, String value) {
        return value.equalsIgnoreCase(valueSearch);
    }
}
