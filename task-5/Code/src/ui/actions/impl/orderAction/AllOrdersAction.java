package ui.actions.impl.orderAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import ui.actions.IAction;

import java.util.ArrayList;
import java.util.List;

public class AllOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public AllOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        List<Order> allOrders = new ArrayList<>(serviceManager.getOrders());
        for (int i = 0; i < allOrders.size(); i++) {
            System.out.println((i + 1) + ". " + allOrders.get(i).getDescription());
        }
    }
}
