package autoservice.ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class AllGaragePlacesAction implements IAction {
    private final ServiceManager serviceManager;

    public AllGaragePlacesAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            List<GaragePlace> allGaragePlaces = new ArrayList<>(serviceManager.allGaragePlaces());
            if (allGaragePlaces.isEmpty()) {
                System.out.println("No garage places are available.");
                return;
            }
            for (GaragePlace garagePlace : allGaragePlaces) {
                try {
                    System.out.println("Place Number: " + garagePlace.getPlaceNumber() + " " +
                            "Status: " + (garagePlace.isOccupied() ? "Occupied" : "Available"));
                } catch (Exception e) {
                    System.out.println("Error retrieving information for garage place number " +
                            garagePlace.getPlaceNumber() + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving all garage places: " + e.getMessage());
        }
    }
}
