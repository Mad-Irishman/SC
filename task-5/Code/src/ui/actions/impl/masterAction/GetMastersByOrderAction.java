package ui.actions.impl.masterAction;

import autoservice.assistantManager.exception.AssistantException;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import ui.actions.IAction;

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
            System.out.print("Введите описание заказа, для которого нужно найти мастеров: ");
            String orderDescription = scanner.nextLine();

            Order order = serviceManager.getOrderByDescription(orderDescription);
            if (order == null) {
                System.out.println("Заказ с таким описанием не найден.");
                return;
            }

            List<Master> masters = serviceManager.getMastersByOrders(serviceManager.getMasters(), order);


            if (masters.isEmpty()) {
                System.out.println("Мастеров, работающих над этим заказом, не найдено.");
            } else {
                System.out.println("Мастера, работающие над заказом '" + orderDescription + "':");
                for (Master master : masters) {
                    System.out.println(" - " + master.getName());
                }
            }
        } catch (AssistantException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
        }
    }
}
