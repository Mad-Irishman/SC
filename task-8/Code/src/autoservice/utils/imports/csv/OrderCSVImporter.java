package autoservice.utils.imports.csv;

import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.models.master.Master;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.manager.impl.ServiceManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderCSVImporter {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String filePath = "src/autoservice/resources/importFiles/orders.csv";

    public static void importOrdersFromCSV(ServiceManager serviceManager) throws IOException {
        List<Order> existingOrders = serviceManager.getOrders();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != 9) {
                    throw new IOException("Invalid data format: " + line);
                }

                String id = values[0].trim();
                String description = values[1].trim();
                String masterName = values[2].trim();
                String garagePlaceNumber = values[3].trim();
                String statusString = values[4].trim();
                String submissionDateStr = values[5].trim();
                String completionDateStr = values[6].trim();
                String plannedStartDateStr = values[7].trim();
                String priceStr = values[8].trim();

                OrderStatus status = OrderStatus.valueOf(statusString.toUpperCase());
                LocalDateTime submissionDate = submissionDateStr.isEmpty() ? null : LocalDateTime.parse(submissionDateStr, DATE_FORMAT);
                LocalDateTime completionDate = completionDateStr.isEmpty() ? null : LocalDateTime.parse(completionDateStr, DATE_FORMAT);
                LocalDateTime plannedStartDate = plannedStartDateStr.isEmpty() ? null : LocalDateTime.parse(plannedStartDateStr, DATE_FORMAT);
                double price = Double.parseDouble(priceStr);

                Master assignedMaster = serviceManager.findMasterByName(masterName);
                GaragePlace assignedGaragePlace = serviceManager.findGaragePlaceByNumber(garagePlaceNumber);

                boolean orderExists = existingOrders.stream()
                        .anyMatch(order -> order.getIdOrder().equals(id) || order.getDescription().equals(description));

                if (!orderExists) {
                    Order newOrder = new Order(description, submissionDate, completionDate, plannedStartDate, price);
                    newOrder.setStatusOrder(status);
                    newOrder.setAssignedMaster(assignedMaster);
                    newOrder.setAssignedGaragePlace(assignedGaragePlace);

                    serviceManager.addOrder(newOrder);
                    System.out.println("Order added: " + newOrder.getDescription());
                } else {
                    System.out.println("Order already exists: " + description);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }
}
