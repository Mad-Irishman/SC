package autoservice.ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garage.Garage;
import autoservice.models.garage.garageStatus.GarageStatus;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.ui.actions.IAction;

import java.util.List;

public class GetAvailableGaragePlaces implements IAction {
    private final ServiceManager serviceManager;

    public GetAvailableGaragePlaces(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            Garage garage = serviceManager.getGarage();
            if (garage == null) {
                System.out.println("No garage found.");
                return;
            }

            if (garage.getIsAvailable() != GarageStatus.AVAILABLE) {
                System.out.println("Garage is not available.");
                return;
            }

            List<GaragePlace> availableGaragePlaces = garage.getAvailableGaragePlaces();
            if (availableGaragePlaces == null || availableGaragePlaces.isEmpty()) {
                System.out.println("No available garage places found.");
                return;
            }

            System.out.println("Available garage places:");
            for (GaragePlace place : availableGaragePlaces) {
                try {
                    System.out.println("Place Number: " + place.getPlaceNumber());
                } catch (Exception e) {
                    System.out.println("Error retrieving information for place number " +
                            (place != null ? place.getPlaceNumber() : "unknown") + ": " + e.getMessage());
                }
            }

        } catch (RuntimeException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
