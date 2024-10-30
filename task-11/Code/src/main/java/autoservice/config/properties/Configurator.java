package autoservice.config.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {

    public static void configure(Object obj) throws IllegalAccessException, IOException {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
            if (annotation != null) {
                String configFileName = annotation.configFileName();
                String propertyName = annotation.propertyName();
                Class<?> fieldType = annotation.type();
                Properties properties = loadProperties(configFileName);
                String value = properties.getProperty(propertyName);
                System.out.println("Считываемое значение для " + propertyName + ": " + value);
                if (value != null) {
                    Object convertedValue = convertValue(value, fieldType);
                    field.set(obj, convertedValue);
                    System.out.println("Установлено значение для " + propertyName + ": " + convertedValue);
                } else {
                    System.out.println("Значение для " + propertyName + " не найдено.");
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
