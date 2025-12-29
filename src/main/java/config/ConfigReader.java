package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not found at src/test/resources");
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static String getBrowser() {
        // Check if browser is set via system property (for parallel execution)
        String browser = System.getProperty("browser");
        if (browser != null && !browser.isEmpty()) {
            System.out.println("Using browser from parameter: " + browser);
            return browser;
        }
        // Otherwise read from config file
        browser = getProperty("browser");
        System.out.println("Using browser from config: " + browser);
        return browser;
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait"));
    }
}
