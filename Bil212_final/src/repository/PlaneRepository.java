package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.PlaneDTO;

import java.io.File;
import java.io.IOException;

public class PlaneRepository {
    public PlaneDTO readPlaneInfo(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // Для поддержки LocalDateTime
            return mapper.readValue(new File(filename), PlaneDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
