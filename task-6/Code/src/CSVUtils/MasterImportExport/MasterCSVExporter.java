package CSVUtils.MasterImportExport;

import autoservice.models.master.Master;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MasterCSVExporter {
    public static void exportMastersToCSV(List<Master> masters, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,Specialization\n");

            for (Master master : masters) {
                writer.write(master.toString() + "\n");
            }
        }
    }
}