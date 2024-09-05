package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;

import java.util.List;

public class GetAvailableGaragePlaces implements IAction {
    private final ServiceManager serviceManager;

    public GetAvailableGaragePlaces(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            List<Garage> garages = serviceManager.getGarages();
            if (garages == null) {
                throw new RuntimeException("Failed to retrieve garages. The list is null.");
            }

            List<GaragePlace> availableGaragePlaces = serviceManager.getAvailableGaragePlaces(garages);
            if (availableGaragePlaces == null) {
                throw new RuntimeException("Failed to retrieve available garage places. The list is null.");
            }

            if (availableGaragePlaces.isEmpty()) {
                System.out.println("No available garage places found.");
            } else {
                System.out.println("Available garage places:");
                for (GaragePlace place : availableGaragePlaces) {
                    try {
                        System.out.println("Place Number: " + place.getPlaceNumber());
                    } catch (Exception e) {
                        System.out.println("Error retrieving information for place number " +
                                (place != null ? place.getPlaceNumber() : "unknown") + ": " + e.getMessage());
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
