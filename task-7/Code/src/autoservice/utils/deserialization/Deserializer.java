package autoservice.utils.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/autoservice/resources/deserialization";

    public static <T> T deserializeFromFile(String filePath, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            return null;
        }
    }
}