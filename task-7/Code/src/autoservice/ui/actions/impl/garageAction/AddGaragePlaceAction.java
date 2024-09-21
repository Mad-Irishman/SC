package autoservice.ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.ui.actions.IAction;

import java.util.Scanner;

public class AddGaragePlaceAction implements IAction {
    private final ServiceManager serviceManager;

    public AddGaragePlaceAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int placeNumber = -1;

        while (placeNumber <= 0) {
            System.out.println("Enter the number of the new garage place: ");
            if (scanner.hasNextInt()) {
                placeNumber = scanner.nextInt();
                if (placeNumber <= 0) {
                    System.out.println("The place number must be a positive integer. Please try again.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }

        try {
            GaragePlace garagePlace = new GaragePlace(placeNumber);
            serviceManager.addGaragePlace(garagePlace);
            System.out.println("Garage place " + placeNumber + " has been successfully added.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error while adding garage place: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the garage place: " + e.getMessage());
        }
    }
}