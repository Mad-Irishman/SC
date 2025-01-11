package autoservice.utils.export.csv;

import autoservice.dto.orderDTO.differentDTO.OrderDTOForGet;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.models.master.Master;
import autoservice.models.garagePlace.GaragePlace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderCSVExporter {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String filePath = "src/main/resources/exportFiles/orders.csv";

    public static void exportOrdersToCSV(List<OrderDTOForGet> orders) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Description, Assigned Master, Assigned Garage Place, Status, Submission Date, Completion Date, Planned Start Date, Price");
            writer.newLine();

            for (OrderDTOForGet order : orders) {
                String id = order.getOrderId() != null ? order.getOrderId() : "N/A";
                String description = order.getDescription() != null ? order.getDescription() : "N/A";
                String price = String.valueOf(order.getPrice());

                String line = String.format("%s,%s,%s",
                        id,
                        description,
                        price
                );

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing orders to file: " + e.getMessage(), e);
        }
    }

}
