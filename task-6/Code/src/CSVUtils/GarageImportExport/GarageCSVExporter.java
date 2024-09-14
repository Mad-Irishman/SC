package CSVUtils.GarageImportExport;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GarageCSVExporter {
    public static void exportGaragesToCSV(List<Garage> garages, List<Master> masters, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("GarageID, IsAvailable");
            writer.newLine();
            for (Garage garage : garages) {
                writer.write(garage.getId() + "," + garage.getIsAvailable());
            }

            writer.newLine();
            writer.write("GarageID, MasterID, MasterName, MasterStatus");
            for (Garage garage : garages) {
                for (Master master : garage.getAllMasters()) {
                    writer.write(garage.getId() + "," + master.getId() + "," + master.getName() + "," + master.isAvailable());
                }
            }

            writer.newLine();
            writer.write("GarageID, PlaceNumber, Occupied");
            for (Garage garage : garages) {
                for (GaragePlace place : garage.getGaragePlaces()) {
                    writer.write(garage.getId() + "," + place.getPlaceNumber() + "," + place.isOccupied());
                }
            }
            writer.newLine();
            System.out.println("Garage data exported successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting garage data: " + e.getMessage());
        }
    }
}

