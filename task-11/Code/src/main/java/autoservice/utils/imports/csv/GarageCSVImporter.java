package autoservice.utils.imports.csv;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GarageCSVImporter {
    private static final String filePath = "src/main/resources/importFiles/garages.csv";

    public static void importGaragePlacesFromCSV(ServiceManager serviceManager) throws IOException {
        List<GaragePlace> existingGaragePlaces = serviceManager.allGaragePlaces();
        List<GaragePlace> newGaragePlaces = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length != 2) {
                    throw new IOException("Invalid data format: " + line);
                }

                int placeId = Integer.parseInt(values[0]);
                boolean isOccupied = Boolean.parseBoolean(values[1]);

                boolean idExistsInDB = existingGaragePlaces.stream()
                        .anyMatch(garagePlace -> garagePlace.getPlaceNumber() == placeId);

                if (idExistsInDB) {
                    System.out.println("ID conflict found: " + placeId + ". Import canceled.");
                    return;
                }

                GaragePlace newGaragePlace = new GaragePlace(placeId, isOccupied);
                newGaragePlaces.add(newGaragePlace);
            }

            for (GaragePlace garagePlace : newGaragePlaces) {
                serviceManager.addGaragePlace(garagePlace);
                System.out.println("Garage place added: " + garagePlace.getPlaceNumber());
            }

        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }

}
