package autoservice.utils.imports.csv;

import autoservice.dto.orderDTO.differentDTO.OrderDTOForGet;
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
import java.util.ArrayList;
import java.util.List;

public class OrderCSVImporter {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String filePath = "src/main/resources/importFiles/orders.csv";

    public static void importOrdersFromCSV(ServiceManager serviceManager) throws IOException {
        List<OrderDTOForGet> existingOrders = serviceManager.getOrders();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            List<Order> newOrders = new ArrayList<>();

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
                String masterId = values[2].trim();
                int garagePlaceNumber = Integer.parseInt(values[3].trim());
                String statusString = values[4].trim();
                String submissionDateStr = values[5].trim();
                String completionDateStr = values[6].trim();
                String plannedStartDateStr = values[7].trim();
                double price = Double.parseDouble(values[8].trim());

                OrderStatus status = OrderStatus.valueOf(statusString.toUpperCase());

                LocalDateTime submissionDate = LocalDateTime.parse(submissionDateStr, DATE_FORMAT);
                LocalDateTime completionDate = LocalDateTime.parse(completionDateStr, DATE_FORMAT);
                LocalDateTime plannedStartDate = LocalDateTime.parse(plannedStartDateStr, DATE_FORMAT);

                Master assignedMaster = serviceManager.getMasterById(masterId);
                GaragePlace assignedGaragePlace = serviceManager.findGaragePlaceByNumber(String.valueOf(garagePlaceNumber));

                boolean orderExists = existingOrders.stream()
                        .anyMatch(order -> order.getOrderId().equals(id) || order.getDescription().equals(description));

                if (orderExists) {
                    System.out.println("Duplicate order found with ID: " + id + ". Import cancelled.");
                    return;
                }

                Order newOrder = new Order(id, description, submissionDate, completionDate, plannedStartDate, price);
                newOrder.setStatusOrder(status);
                newOrder.setAssignedMaster(assignedMaster);
                newOrder.setAssignedGaragePlace(assignedGaragePlace);

                newOrders.add(newOrder);
            }

            for (Order order : newOrders) {
                serviceManager.addOrder(order);
                System.out.println("Order added: " + order.getDescription());
            }

        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }

}
