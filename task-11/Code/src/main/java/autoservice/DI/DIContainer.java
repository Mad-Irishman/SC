package autoservice.DI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class DIContainer {
    private Map<Class<?>, Object> instances = new HashMap<>();
    public <T> T getInstance(Class<T> clazz) {
        try {
            if (instances.containsKey(clazz)) {
                return clazz.cast(instances.get(clazz));
            }
            if (clazz.isInterface()) {
                T instance = resolveInterface(clazz);
                if (instance != null) {
                    instances.put(clazz, instance);
                    return instance;
                }
            }
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = getInstance(parameterTypes[i]);
                }
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance(parameters);
                instances.put(clazz, instance);
                injectDependencies(instance);
                return instance;
            }
            throw new RuntimeException("Не найден конструктор для класса: " + clazz);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании экземпляра класса: " + clazz, e);
        }
    }

    private <T> void injectDependencies(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Class<?> dependencyClass = field.getType();
                Object dependency = getInstance(dependencyClass);
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }
    }

    private <T> T resolveInterface(Class<T> clazz) {
        if (clazz == List.class) {
            return (T) new ArrayList<>();
        }
        if (clazz == Map.class) {
            return (T) new HashMap<>();
        }
        if (clazz == Set.class) {
            return (T) new HashSet<>();
        }
        throw new RuntimeException("Не найдена реализация для интерфейса: " + clazz);
    }
}
