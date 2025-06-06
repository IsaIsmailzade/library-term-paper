package library.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public final class PropertiesUtil {

    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
        PROPERTIES.load(inputStream);
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
