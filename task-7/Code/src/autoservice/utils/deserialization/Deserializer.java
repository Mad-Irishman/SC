package autoservice.utils.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/autoservice/resources/start_end_program/start_end_program.json";

    public static <T> T deserializeFromFile(Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}