package com.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            // Читаем из classpath
            InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
            input.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}