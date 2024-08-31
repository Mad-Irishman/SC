package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;

import java.util.Scanner;

public class AddGaragePlaceAction implements IAction {
    private final ServiceManager serviceManager;

    public AddGaragePlaceAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер нового гаражнего места: ");
        int placeNumber = -1;

        while (placeNumber <= 0) {
            if (scanner.hasNextInt()) {
                placeNumber = scanner.nextInt();
                if (placeNumber <= 0) {
                    System.out.println("Номер места должен быть положительным числом. Попробуйте еще раз.");
                }
            } else {
                System.out.println("Введите целое число.");
                scanner.next();
            }
        }

        try {
            GaragePlace garagePlace = new GaragePlace(placeNumber);
            serviceManager.addGaragePlace(garagePlace);
            System.out.println("Гаражное место " + placeNumber + " успешно добавлено.");
        } catch (Exception e) {
            System.out.println("Ошибка при добавление гаражного места: " + e.getMessage());
        }
    }
}
