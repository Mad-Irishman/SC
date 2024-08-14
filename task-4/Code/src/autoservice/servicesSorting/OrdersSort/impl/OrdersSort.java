package autoservice.servicesSorting.OrdersSort.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.OrdersSort.OrdersSortInterface;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersSort implements OrdersSortInterface {
    private ServiceManager serviceManager;

    public List<Order> getSortedOrders(List<Comparator<Order>> comparators) {
        return serviceManager.getOrders().stream()
                .sorted(combineComparators(comparators))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByMaster(Master master) {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getAssignedMaster().equals(master))
                .collect(Collectors.toList());
    }

    public List<Order> getCurrentOrders() {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return serviceManager.getOrders().stream()
                .filter(order -> order.getStatusOrder() == status)
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        return orders.stream()
                .filter(order -> order.getCompletionDate() != null &&
                        !order.getCompletionDate().isBefore(startTime) &&
                        !order.getCompletionDate().isAfter(endTime))
                .collect(Collectors.toList());
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        return comparators.stream()
                .reduce(Comparator::thenComparing)
                .orElseThrow(() -> new IllegalArgumentException("Список компараторов не должен быть пустым"));
    }
}
