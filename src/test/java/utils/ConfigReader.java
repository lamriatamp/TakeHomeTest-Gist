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
            // di CI file memang tidak ada, jadi abaikan
        }
    }

    public static String get(String key) {
        // cek dulu di environment variable (untuk CI/CD)
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue;
        }
        // kalau tidak ada, ambil dari config.properties (untuk lokal dev)
        return properties.getProperty(key);
    }
}