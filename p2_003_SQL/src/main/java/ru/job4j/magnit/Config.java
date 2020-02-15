package ru.job4j.magnit;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String CONFIG = "magnit.properties";
    private final Properties values = new Properties();

    public Config() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(CONFIG)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String url() {
        return get("url");
    }

    private String get(String key) {
        return this.values.getProperty(key);
    }
}