package ozon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public enum TestProperties {

    INSTANCE;

    private final Properties properties = new Properties();

    TestProperties() {

        try {
            FileInputStream inputStream = new FileInputStream("src/main/java/ozon/config/config.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
