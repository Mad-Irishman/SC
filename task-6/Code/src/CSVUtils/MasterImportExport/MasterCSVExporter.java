package CSVUtils.MasterImportExport;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MasterCSVExporter {
    public static void exportMastersToCSV(List<Master> masters, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Name, Status");
            writer.newLine();

            for (Master master : masters) {
                String id = master.getId();
                String name = master.getName();
                MasterStatus status = master.isAvailable();

                String line = String.format("%s, %s, %s", id, name, status);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage(), e);
        }
    }
}