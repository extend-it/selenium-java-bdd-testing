package com.example.saucedemo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("could not load config.properties", e);
        }
        return props;
    }

    public static String get(String key, String defaultValue) {
        return System.getProperty(key, PROPERTIES.getProperty(key, defaultValue));
    }

    public static int getInt(String key, int defaultValue) {
        // Properties files are easy to leave with trailing whitespace after
        // an edit; Integer.parseInt() has zero tolerance for it.
        String raw = get(key, String.valueOf(defaultValue));
        return Integer.parseInt(raw.trim());
    }
}
