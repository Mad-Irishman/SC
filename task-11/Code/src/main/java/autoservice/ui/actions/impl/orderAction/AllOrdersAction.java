package autoservice.ui.actions.impl.orderAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import autoservice.ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class AllOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public AllOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            List<Order> allOrders = serviceManager.getOrders();

            if (allOrders.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                for (int i = 0; i < allOrders.size(); i++) {
                    System.out.println((i + 1) + ". " + allOrders.get(i).getDescription());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving orders: " + e.getMessage());
        }
    }
}
