package autoservice.ui.actions.impl.masterAction;

import autoservice.exception.assistantException.exception.AssistantException;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.ui.actions.IAction;

import java.util.List;
import java.util.Scanner;

public class GetMastersByOrderAction implements IAction {
    private final ServiceManager serviceManager;

    public GetMastersByOrderAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the description of the order to find the masters: ");
            String orderDescription = scanner.nextLine();
            Order order = serviceManager.getOrderByDescription(orderDescription);

            if (order == null) {
                System.out.println("Order with the specified description was not found.");
                return;
            }

            List<Master> masters = serviceManager.getMastersByOrders(serviceManager.getMasters(), order);

            if (masters.isEmpty()) {
                System.out.println("No masters are working on this order.");
            } else {
                System.out.println("Masters working on the order '" + orderDescription + "':");
                for (Master master : masters) {
                    System.out.println(" - " + master.getName());
                }
            }
        } catch (AssistantException e) {
            System.out.println("Error retrieving data from assistant manager: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }
}
