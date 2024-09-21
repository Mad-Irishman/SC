package autoservice.utils.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Serializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/autoservice/resources/serialization";

    public static <T> void serializeToFile(T object, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
