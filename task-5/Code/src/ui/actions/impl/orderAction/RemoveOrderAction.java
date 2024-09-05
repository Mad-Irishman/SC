package ui.actions.impl.orderAction;

import autoservice.manager.exception.ServiceManagerException;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import ui.actions.IAction;

import java.util.Scanner;

public class RemoveOrderAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveOrderAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the description of the order you want to remove: ");
            String description = scanner.nextLine();

            Order orderToRemove = null;
            for (Order order : serviceManager.getOrders()) {
                if (order.getDescription().equalsIgnoreCase(description.trim())) {
                    orderToRemove = order;
                    break;
                }
            }

            if (orderToRemove != null) {
                serviceManager.removeOrder(orderToRemove);
                System.out.println("Order with description \"" + description + "\" was successfully removed.");
            } else {
                System.out.println("No order found with the given description.");
            }
        } catch (ServiceManagerException e) {
            System.out.println("Error removing order: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}
