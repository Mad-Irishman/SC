package autoservice.utils.export.csv;

import autoservice.dto.masterDTO.differentDTO.MasterDTOForGet;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MasterCSVExporter {
    private static final String filePath = "src/main/resources/exportFiles/masters.csv";

    public static void exportMastersToCSV(List<MasterDTOForGet> masters) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,Status");
            writer.newLine();

            for (MasterDTOForGet master : masters) {
                String id = master.getId();
                String name = master.getName();

                String line = String.format("%s,%s", id, name);
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Export completed successfully.");

        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage(), e);
        }
    }

}
