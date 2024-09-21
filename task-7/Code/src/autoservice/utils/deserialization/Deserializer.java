package autoservice.utils.deserialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer {

    public static <T> T deserialize(String fileName, Class<T> clazz) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return clazz.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}