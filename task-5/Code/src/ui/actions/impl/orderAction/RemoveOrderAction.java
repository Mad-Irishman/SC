package ui.actions.impl.orderAction;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import ui.actions.IAction;

import java.util.Scanner;

public class RemoveOrderAction implements IAction {
    private final ServiceManager serviceManager;

    public RemoveOrderAction(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }


    // При удаление пишет, что есть ошибка, но при этом заказ удаляет
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите описание заказа, который хотите удалить: ");
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
                System.out.println("Заказ с описанием \"" + description + "\" был успешно удален.");
            } else {
                System.out.println("Заказ с таким описанием не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении заказа: " + e.getMessage());
        }
    }
}
