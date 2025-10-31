package com.gbg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigLoader {

    private static final Logger logger = Logger.getLogger(ConfigLoader.class.getName());
    private static final Properties properties = new Properties();

    static {
        Path configPath = Paths.get(System.getProperty("config.path",
                Paths.get("src", "test", "resources", "config.properties").toAbsolutePath().toString()));
        if (Files.exists(configPath)) {
            try (InputStream input = Files.newInputStream(configPath)) {
                properties.load(input);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error reading properties file!", e);
            }
        } else {
            logger.log(Level.SEVERE, "Properties file not found at: " + configPath);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        String value = get(key);
        return Boolean.parseBoolean(value);
    }

    public static int getInt(String key) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "Invalid or missing integer for key: " + key, e);
            return 0;
        }
    }

//    public static String get(String key, String defaultValue) {
//        return properties.getProperty(key, defaultValue);
//    }
//
//    public static boolean getBoolean(String key, boolean defaultValue) {
//        return Boolean.parseBoolean(get(key, Boolean.toString(defaultValue)));
//    }
//
//    public static int getInt(String key, int defaultValue) {
//        try {
//            return Integer.parseInt(get(key, Integer.toString(defaultValue)));
//        } catch (NumberFormatException e) {
//            logger.log(Level.WARNING, "Invalid integer for key: " + key, e);
//            return defaultValue;
//        }
//    }
}