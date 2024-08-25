package autoservice.servicesSorting.OrdersSort.impl;

import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.OrdersSort.OrdersSortInterface;
import autoservice.servicesSorting.OrdersSort.exception.OrdersSortException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersSort implements OrdersSortInterface {

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) {
        if (comparators == null) {
            throw new OrdersSortException("Список компараторов не может быть null");
        }

        try {
            return orders.stream()
                    .sorted(combineComparators(comparators))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrdersSortException("Произошла ошибка при сортировке заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByMaster(List<Order> orders, Master master) {
        if (master == null) {
            throw new OrdersSortException("Мастер не может быть null");
        }

        try {
            return orders.stream()
                    .filter(order -> order.getAssignedMaster().equals(master))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrdersSortException("Произошла ошибка при получении заказов по мастеру. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) {
        try {
            return orders.stream()
                    .filter(order -> order.getStatusOrder() == OrderStatus.IN_PROGRESS)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrdersSortException("Произошла ошибка при получении текущих заказов. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status) {
        if (status == null) {
            throw new OrdersSortException("Статус заказа не может быть null");
        }

        try {
            return orders.stream()
                    .filter(order -> order.getStatusOrder() == status)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrdersSortException("Произошла ошибка при получении заказов по статусу. Попробуйте снова позже.");
        }
    }

    @Override
    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        if (orders == null || startTime == null || endTime == null) {
            throw new OrdersSortException("Параметры не могут быть null");
        }

        if (startTime.isAfter(endTime)) {
            throw new OrdersSortException("Время начала не может быть после времени окончания");
        }

        try {
            return orders.stream()
                    .filter(order -> order.getCompletionDate() != null &&
                            !order.getCompletionDate().isBefore(startTime) &&
                            !order.getCompletionDate().isAfter(endTime))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new OrdersSortException("Произошла ошибка при получении заказов по временным рамкам. Попробуйте снова позже.");
        }
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        if (comparators == null) {
            throw new OrdersSortException("Список компараторов не может быть null");
        }

        return comparators.stream()
                .reduce(Comparator::thenComparing)
                .orElseThrow(() -> new OrdersSortException("Список компараторов не должен быть пустым"));
    }
}
