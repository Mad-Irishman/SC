package autoservice.utils.imports.csv;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MasterCSVImporter {
    private static final String filePath = "src/main/resources/importFiles/masters.csv";

    public static void importMastersFromCSV(ServiceManager serviceManager) throws IOException {
        List<Master> existingMasters = serviceManager.getAllMasterInGarage();
        List<Master> newMasters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != 3) {
                    throw new IOException("Invalid data format: " + line);
                }

                String id = values[0];
                String name = values[1];
                MasterStatus status = MasterStatus.valueOf(values[2]);

                boolean idExistsInDB = existingMasters.stream()
                        .anyMatch(master -> master.getId().equals(id));

                if (idExistsInDB) {
                    System.out.println("ID conflict found: " + id + ". Import canceled.");
                    return;
                }

                Master newMaster = new Master(id, name, status);
                newMasters.add(newMaster);
            }

            for (Master master : newMasters) {
                serviceManager.addMaster(master);
                System.out.println("Master added: " + master.getName());
            }

        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }


//    private static Order parseOrderFromString(String orderString) {
//        String[] parts = orderString
//                .replace("Order{", "")
//                .replace("}", "")
//                .split(", ");
//
//        String description = parts[1].split("=")[1].replace("'", ""); // Очищаем от кавычек
//        LocalDateTime submissionDate = LocalDateTime.parse(parts[2].split("=")[1].replace("'", ""));
//        LocalDateTime completionDate = LocalDateTime.parse(parts[3].split("=")[1].replace("'", ""));
//        LocalDateTime plannedStartDate = LocalDateTime.parse(parts[4].split("=")[1].replace("'", ""));
//        double price = Double.parseDouble(parts[5].split("=")[1]);
//
//        return new Order(description, submissionDate, completionDate, plannedStartDate, price);
//    }

}
