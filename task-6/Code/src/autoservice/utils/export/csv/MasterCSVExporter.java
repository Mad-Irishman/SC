package autoservice.utils.export.csv;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MasterCSVExporter {
    private static final String filePath = "src/autoservice/resources/exportFiles/masters.csv";

    public static void exportMastersToCSV(List<Master> masters) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Name, Status, Order_id");
            writer.newLine();

            for (Master master : masters) {
                String id = master.getId();
                String name = master.getName();
                MasterStatus status = master.isAvailable();
                //если нет заказа, то мастера создать невозможно, но при этом и заказа создать невозможно потому что нет мастера
                String order_id = master.getOrderMaster().getIdOrder();

                String line = String.format("%s, %s, %s, %s", id, name, status, order_id);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage(), e);
        }
    }
}