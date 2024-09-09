package CSVUtils.OrderImportExport;

import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderCSVExporter {
    public static void exportOrdersToCSV(List<Order> orders, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Description, Status");
            writer.newLine();

            for (Order order : orders) {
                int id = order.getIdOrder();
                String description = order.getDescription();
                OrderStatus status = order.getStatusOrder();

                String line = String.format("%d,%s,%s", id, description, status);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + e.getMessage());
        }
    }
}
