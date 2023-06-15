package com.universal.hibernate.app.configuration;

import com.universal.hibernate.app.util.Constants;

import java.util.Optional;
import java.util.Properties;


public class Environment {

    private final Properties properties;

    public Environment(Properties properties) {
        this.properties = properties;
    }

    public static Environment load() {
        try {
            Properties properties = new Properties();
            properties.load(Environment.class.getClassLoader().getResourceAsStream(Constants.DEFAULT_APP_FILE_NAME));
            return new Environment(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getObject(String key) {
        return properties.get(key);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Optional<String> getPropertyOptional(String key) {
        return Optional.ofNullable(properties.getProperty(key));
    }
}
