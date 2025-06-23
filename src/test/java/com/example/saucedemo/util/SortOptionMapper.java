package com.example.saucedemo.util;

import java.util.Map;

public final class SortOptionMapper {

    private static final Map<String, String> OPTION_VALUES = Map.of(
        "name a to z", "az",
        "name z to a", "za",
        "price low to high", "hilo",
        "price high to low", "lohi"
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
