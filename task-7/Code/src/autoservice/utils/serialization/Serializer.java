package autoservice.utils.serialization;

import java.io.*;

public class Serializer {

    public static <T extends Serializable> void serialize(T obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(obj);
            System.out.println("Object serialized: " + obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
