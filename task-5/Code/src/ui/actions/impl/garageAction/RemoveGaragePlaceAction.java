package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;
import ui.actions.impl.garageAction.exception.GaragePlaceNotFoundException;

import java.util.Scanner;

public class RemoveGaragePlaceAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveGaragePlaceAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int placeNumber = -1;

        while (placeNumber <= 0) {
            System.out.println("Enter the number of the garage place to remove: ");
            if (scanner.hasNextInt()) {
                placeNumber = scanner.nextInt();
                if (placeNumber <= 0) {
                    System.out.println("Place number must be a positive number. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }

        try {
            removeGaragePlace(placeNumber);
        } catch (GaragePlaceNotFoundException e) {
            System.out.println("Garage place with number " + placeNumber + " not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while removing the garage place: " + e.getMessage());
        }
    }

    private void removeGaragePlace(int placeNumber) throws GaragePlaceNotFoundException {
        GaragePlace placeToRemove = serviceManager.getGaragePlaceByNumber(placeNumber);
        if (placeToRemove != null) {
            serviceManager.removeGaragePlace(placeToRemove);
            System.out.println("Garage place " + placeNumber + " successfully removed.");
        } else {
            throw new GaragePlaceNotFoundException("Garage place with number " + placeNumber + " does not exist.");
        }
    }
}
