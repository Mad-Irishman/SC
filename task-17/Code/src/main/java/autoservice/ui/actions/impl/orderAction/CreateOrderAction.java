package autoservice.ui.actions.impl.orderAction;

import autoservice.exception.managerException.ServiceManagerException;
import autoservice.manager.impl.ServiceManager;
import autoservice.ui.actions.IAction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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

        System.out.println("Enter order description:");
        String description = scanner.nextLine();

        LocalDateTime submissionDate = null;
        LocalDateTime completionDate = null;
        LocalDateTime plannedStartDate = null;
        double price = 0.0;

        try {
            System.out.println("Enter submission date (format: yyyy-MM-dd HH:mm):");
            submissionDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Enter completion date (format: yyyy-MM-dd HH:mm):");
            completionDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Enter planned start date (format: yyyy-MM-dd HH:mm):");
            plannedStartDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Enter price:");
            price = scanner.nextDouble();
            scanner.nextLine();

            serviceManager.createOrder(description, submissionDate, completionDate, plannedStartDate, price);
            System.out.println("Order created successfully.");

        } catch (DateTimeParseException e) {
            System.err.println("Error: Invalid date format. Please use the format yyyy-MM-dd HH:mm.");
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input. Please enter a valid number for the price.");
        } catch (ServiceManagerException e) {
            System.err.println("Error creating order: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
