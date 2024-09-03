package ui.actions.impl.orderAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import ui.actions.IAction;
import java.util.List;

public class GetCurentOrdersAction implements IAction {
    private final ServiceManager serviceManager;

    public GetCurentOrdersAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public void execute() {
        try {
            List<Order> allOrders = serviceManager.getOrders();
            List<Order> currentOrders = serviceManager.getCurrentOrders(allOrders);

            if (currentOrders.isEmpty()) {
                System.out.println("Нет текущих заказов.");
            } else {
                System.out.println("Текущие заказы:");
                for (Order order : currentOrders) {
                    System.out.println(" - Заказ ID: "+
                            ", Дата создания: " + order.getSubmissionDate() +
                            ", Статус: " + order.getStatusOrder() +
                            ", Стоимость: " + order.getPrice());
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при получении текущих заказов: " + e.getMessage());
        }
    }
}
