package autoservice.utils.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class Serializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String filePath = "src/main/resources/start_end_program/start_end_program.json";

    public static <T> void serializeToFile(T object) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
