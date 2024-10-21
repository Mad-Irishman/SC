package autoservice.utils.export.csv;

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
    private static final String filePath = "src/autoservice/resources/exportFiles/orders.csv";

    public static void exportOrdersToCSV(List<Order> orders) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID, Description, Assigned Master, Assigned Garage Place, Status, Submission Date, Completion Date, Planned Start Date, Price");
            writer.newLine();

            for (Order order : orders) {
                String id = String.valueOf(order.getIdOrder());
                String description = order.getDescription();
                Master master = order.getAssignedMaster();
                GaragePlace garagePlace = order.getAssignedGaragePlace();
                OrderStatus status = order.getStatusOrder();
                String submissionDate = order.getSubmissionDate() != null ? order.getSubmissionDate().format(DATE_FORMAT) : "";
                String completionDate = order.getCompletionDate() != null ? order.getCompletionDate().format(DATE_FORMAT) : "";
                String plannedStartDate = order.getPlannedStartDate() != null ? order.getPlannedStartDate().format(DATE_FORMAT) : "";
                String price = String.valueOf(order.getPrice());

                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        id,
                        description,
                        (master != null ? master.getName() : "N/A"),
                        (garagePlace != null ? garagePlace.getPlaceNumber() : "N/A"),
                        status,
                        submissionDate,
                        completionDate,
                        plannedStartDate,
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
