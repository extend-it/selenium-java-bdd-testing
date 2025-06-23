package com.example.saucedemo.util;

import java.util.Map;

public final class SortOptionMapper {

    // "price low to high" -> "lohi" and "price high to low" -> "hilo" -
    // the values themselves read backwards from the labels, and the two
    // were swapped here originally.
    private static final Map<String, String> OPTION_VALUES = Map.of(
        "name a to z", "az",
        "name z to a", "za",
        "price low to high", "lohi",
        "price high to low", "hilo"
    );

    private SortOptionMapper() {
    }

    public static String toOptionValue(String label) {
        String value = OPTION_VALUES.get(label.toLowerCase());
        if (value == null) {
            throw new IllegalArgumentException("unknown sort label: " + label);
        }
        return value;
    }
}
