package autoservice.utils.export.csv;

import autoservice.dto.garagePlaceDTO.differentDTO.GaragePlaceDTOForGet;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GarageCSVExporter {
    private static final String filePath = "src/main/resources/exportFiles/garages.csv";

    public static void exportGaragesToCSV(List<GaragePlaceDTOForGet> garagePlaces) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("PlaceId, Place status");
            writer.newLine();
            for (GaragePlaceDTOForGet garagePlace : garagePlaces) {
                int placeId = garagePlace.getId();
                boolean placeStatus = garagePlace.isStatus();

                String line = String.format("%s, %s", placeId, placeStatus);
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error exporting garage data: " + e.getMessage());
        }
    }
}

