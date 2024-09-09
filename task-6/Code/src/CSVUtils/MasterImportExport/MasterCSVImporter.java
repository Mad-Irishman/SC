package CSVUtils.MasterImportExport;

import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.manager.impl.ServiceManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MasterCSVImporter {
    public static void importMastersFromCSV(ServiceManager serviceManager, String filePath) throws IOException {
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
                if (values.length != 3) {
                    throw new IOException("Invalid data format: " + line);
                }

                String id = values[0].trim();
                String name = values[1].trim();
                MasterStatus status = MasterStatus.valueOf(values[2].trim().toUpperCase());

                boolean masterExists = existingMasters.stream()
                        .anyMatch(master -> master.getId().equals(id) || master.getName().equals(name));

                if (!masterExists) {
                    Master newMaster = new Master(name);
                    newMaster.setAvailable(status);

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
}
