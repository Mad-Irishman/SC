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

        try {
            List<Comparator<Order>> comparators = new ArrayList<>();

            System.out.println("Выберите параметры для сортировки заказов:");
            System.out.println("1. По дате создания");
            System.out.println("2. По статусу заказа");
            System.out.println("3. По стоимости заказа");

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
                        System.out.println("Некорректный выбор: " + option);
                        return;
                }
            }

            List<Order> sortedOrders = serviceManager.getSortedOrders(serviceManager.getOrders(), comparators);

            if (sortedOrders.isEmpty()) {
                System.out.println("Заказы не найдены.");
            } else {
                System.out.println("Отсортированные заказы:");
                for (Order order : sortedOrders) {
                    System.out.println(" - Заказ ID: " +
                            ", Дата создания: " + order.getSubmissionDate() +
                            ", Статус: " + order.getStatusOrder() +
                            ", Стоимость: " + order.getPrice());
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
        }
    }
}
