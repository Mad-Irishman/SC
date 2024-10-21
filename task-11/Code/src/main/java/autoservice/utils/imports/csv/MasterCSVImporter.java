package autoservice.utils.imports.csv;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MasterCSVImporter {
    private static final String filePath = "src/autoservice/resources/importFiles/masters.csv";

    public static void importMastersFromCSV(ServiceManager serviceManager) throws IOException {
        List<Master> existingMasters = serviceManager.getMasters();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != 5) {
                    throw new IOException("Invalid data format: " + line);
                }

                String id = values[0].trim();
                String name = values[1].trim();
                MasterStatus status = MasterStatus.valueOf(values[2].trim().toUpperCase());
                String order_id = values[3].trim();
                String orderLink = values[4].trim();

                boolean masterExists = existingMasters.stream()
                        .anyMatch(master -> master.getId().equals(id) || master.getName().equals(name));

                if (!masterExists) {
                    Master newMaster = new Master(name);
                    newMaster.setAvailable(status);

                    if (order_id.equals("N/A") && orderLink.equals("N/A")) {
                        newMaster.setOrderMaster(null);
                    } else {
                        Order newOrder = parseOrderFromString(orderLink);
                        serviceManager.addOrder(newOrder);

                    }

                    serviceManager.addMaster(newMaster);
                    System.out.println("Master added: " + newMaster.getName());
                } else {
                    System.out.println("Master already exists: " + name);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }

    private static Order parseOrderFromString(String orderString) {
        String[] parts = orderString
                .replace("Order{", "")
                .replace("}", "")
                .split(", ");

        String description = parts[1].split("=")[1].replace("'", ""); // Очищаем от кавычек
        LocalDateTime submissionDate = LocalDateTime.parse(parts[2].split("=")[1].replace("'", ""));
        LocalDateTime completionDate = LocalDateTime.parse(parts[3].split("=")[1].replace("'", ""));
        LocalDateTime plannedStartDate = LocalDateTime.parse(parts[4].split("=")[1].replace("'", ""));
        double price = Double.parseDouble(parts[5].split("=")[1]);

        return new Order(description, submissionDate, completionDate, plannedStartDate, price);
    }

}
