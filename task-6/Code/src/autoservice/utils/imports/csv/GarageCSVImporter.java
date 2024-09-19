package autoservice.utils.imports.csv;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GarageCSVImporter {
    private static final String filePath = "src/autoservice/resources/importFiles/garages.csv";

    public static void importGaragePlacesFromCSV(ServiceManager serviceManager) throws IOException {
        List<GaragePlace> existingGragePlaces = serviceManager.allGaragePlaces();

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

                int placeId = Integer.parseInt(values[0]);
                String orderId = values[1];
                boolean isOccupaid = Boolean.parseBoolean(values[2]);

                boolean garagePlacesExists = existingGragePlaces.stream().anyMatch(garagePlace -> garagePlace.getPlaceNumber() == placeId);

                if (!garagePlacesExists) {
                    GaragePlace newGaragePlace = new GaragePlace(placeId);

                    serviceManager.addGaragePlace(newGaragePlace);
                    System.out.println("Garage place added: " + newGaragePlace.getPlaceNumber());
                } else {
                    System.out.println("Garage place already exists: " + placeId);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new IOException("Error reading the file or processing data: " + e.getMessage(), e);
        }
    }
}
