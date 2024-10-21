package autoservice.utils.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class Deserializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/main/java/autoservice/resources/start_end_program/start_end_program.json";

    public static <T> T deserializeFromFile(Class<T> clazz) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}