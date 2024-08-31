package ui.actions.impl.orderAction;

import autoservice.manager.exception.ServiceManagerException;
import autoservice.manager.impl.ServiceManager;
import ui.actions.IAction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateOrderAction implements IAction {
    private final ServiceManager serviceManager;

    public CreateOrderAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("Введите описание заказа:");
        String description = scanner.nextLine();

        LocalDateTime submissionDate = null;
        LocalDateTime completionDate = null;
        LocalDateTime plannedStartDate = null;

        try {
            System.out.println("Введите дату подачи заявки (формат: гггг-мм-дд чч:мм):");
            submissionDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Введите дату завершения (формат: гггг-мм-дд чч:мм):");
            completionDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Введите запланированную дату начала (формат: гггг-мм-дд чч:мм):");
            plannedStartDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Введите цену:");
            double price = scanner.nextDouble();

            serviceManager.createOrder(description, submissionDate, completionDate, plannedStartDate, price);

        } catch (DateTimeParseException e) {
            System.err.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат гггг-мм-дд чч:мм.");
        } catch (ServiceManagerException e) {
            System.err.println("Ошибка при создании заказа: " + e.getMessage());
        }
    }
}
