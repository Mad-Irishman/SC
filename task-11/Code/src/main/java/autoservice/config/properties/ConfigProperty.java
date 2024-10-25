package autoservice.config.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigProperty {
    String configFileName() default "src/main/java/autoservice/config/properties/config.properties";
    String propertyName() default "";
    Class<?> type() default String.class;
}
