package autoservice.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {

    public static void configure(Object obj) throws IllegalAccessException, IOException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                String configFileName = annotation.configFileName();
                String propertyName = annotation.propertyName();
                Class<?> fieldType = annotation.type();

                Properties properties = loadProperties(configFileName);

                String value = properties.getProperty(propertyName);

                if (value != null) {
                    Object convertedValue = convertValue(value, fieldType);
                    field.setAccessible(true);
                    field.set(obj, convertedValue);
                }
            }
        }
    }

    private static Properties loadProperties(String configFileName) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFileName)) {
            properties.load(input);
        }
        return properties;
    }

    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType == String.class) {
            return value;
        }
        throw new IllegalArgumentException("Unsupported field type: " + targetType);
    }
}
