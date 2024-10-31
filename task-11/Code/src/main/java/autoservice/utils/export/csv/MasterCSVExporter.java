package autoservice.utils.export.csv;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.order.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MasterCSVExporter {
    private static final String filePath = "src/main/resources/exportFiles/masters.csv";

    public static void exportMastersToCSV(List<Master> masters) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Name, Status, Order_id, Order link");
            writer.newLine();

            for (Master master : masters) {
                String id = master.getId();
                String name = master.getName();
                MasterStatus status = master.isAvailable();

                if (master.getOrderMaster() == null) {
                    String line = String.format("%s, %s, %s, %s, %s", id, name, status, "N/A", "N/A");
                    writer.write(line);
                    writer.newLine();
                } else {
                    Order order = master.getOrderMaster();
                    String order_id = order.getIdOrder();
                    String orderLink = order.toString();

                    String line = String.format("%s, %s, %s, %s, %s", id, name, status, order_id, orderLink);
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage(), e);
        }
    }
}
