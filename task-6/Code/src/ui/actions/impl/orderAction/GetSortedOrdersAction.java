package ui.actions.impl.orderAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import ui.actions.IAction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GetSortedOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public GetSortedOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        List<Comparator<Order>> comparators = new ArrayList<>();

        try {
            System.out.println("Choose sorting parameters for orders:");
            System.out.println("1. By submission date");
            System.out.println("2. By order status");
            System.out.println("3. By order price");

            String input = scanner.nextLine();
            String[] options = input.split("\\s+");

            for (String option : options) {
                switch (option) {
                    case "1":
                        comparators.add(Comparator.comparing(Order::getSubmissionDate));
                        break;
                    case "2":
                        comparators.add(Comparator.comparing(Order::getStatusOrder));
                        break;
                    case "3":
                        comparators.add(Comparator.comparing(Order::getPrice));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid option: " + option);
                }
            }

            List<Order> sortedOrders = serviceManager.getSortedOrders(serviceManager.getOrders(), comparators);

            if (sortedOrders.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                System.out.println("Sorted orders:");
                for (Order order : sortedOrders) {
                    System.out.println(" - Order ID: " +
                            ", Submission Date: " + order.getSubmissionDate() +
                            ", Status: " + order.getStatusOrder() +
                            ", Price: " + order.getPrice());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
