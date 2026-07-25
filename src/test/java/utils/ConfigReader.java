package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            File file = new File("config.properties");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                properties.load(fis);
            }
        } catch (IOException e) {

        }
    }

    public static String get(String key) {

        String envValue = System.getenv(key);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue;
        }

        return properties.getProperty(key);
    }
}