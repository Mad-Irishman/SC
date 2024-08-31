package ui.actions.impl.garageAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import ui.actions.IAction;

import java.util.Scanner;

public class RemoveGaragePlaceAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveGaragePlaceAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер наражного места, которе нужно удалить: ");

        int placeNumber = -1;

        while (placeNumber <= 0) {
            if (scanner.hasNextInt()) {
                placeNumber = scanner.nextInt();
                if (placeNumber <= 0) {
                    System.out.println("Номер места должен быть положительным число. Попробуйте снова: ");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next();
            }
        }

        try {
            GaragePlace placeToRemove = serviceManager.getGaragePlaceByNumber(placeNumber);
            if (placeToRemove != null) {
                serviceManager.removeGaragePlace(placeToRemove);
                System.out.println("Гаражное место " + placeNumber + " успешно удалено.");
            } else {
                System.out.println("Гаражное место с номером " + placeNumber + " не найдено.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении гаражного места: " + e.getMessage());
        }
    }
}
