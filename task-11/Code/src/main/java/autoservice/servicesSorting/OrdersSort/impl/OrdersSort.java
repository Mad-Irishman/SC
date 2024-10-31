package autoservice.servicesSorting.OrdersSort.impl;

import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.OrdersSort.OrdersSortInterface;
import autoservice.exception.orderSortException.OrdersSortException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersSort implements OrdersSortInterface {

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) {
        if (comparators == null) {
            throw new OrdersSortException("Comparator list cannot be null.");
        }

        try {
            return orders.stream()
                    .sorted(combineComparators(comparators))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error during sorting orders: " + e.getMessage());
            throw new OrdersSortException("An error occurred while sorting orders. Please try again later.");
        }
    }

    @Override
    public List<Order> getOrdersByMaster(List<Order> orders, Master master) {
        if (master == null) {
            throw new OrdersSortException("Master cannot be null.");
        }

        try {
            return orders.stream()
                    .filter(order -> master.equals(order.getAssignedMaster()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error retrieving orders by master: " + e.getMessage());
            throw new OrdersSortException("An error occurred while retrieving orders by master. Please try again later.");
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) {
        try {
            return orders.stream()
                    .filter(order -> OrderStatus.IN_PROGRESS.equals(order.getStatusOrder()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error retrieving current orders: " + e.getMessage());
            throw new OrdersSortException("An error occurred while retrieving current orders. Please try again later.");
        }
    }

    @Override
    public List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status) {
        if (status == null) {
            throw new OrdersSortException("Order status cannot be null.");
        }

        try {
            return orders.stream()
                    .filter(order -> status.equals(order.getStatusOrder()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error retrieving orders by status: " + e.getMessage());
            throw new OrdersSortException("An error occurred while retrieving orders by status. Please try again later.");
        }
    }

    @Override
    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        if (orders == null || startTime == null || endTime == null) {
            throw new OrdersSortException("Parameters cannot be null.");
        }

        if (startTime.isAfter(endTime)) {
            throw new OrdersSortException("Start time cannot be after end time.");
        }

        try {
            return orders.stream()
                    .filter(order -> order.getCompletionDate() != null &&
                            !order.getCompletionDate().isBefore(startTime) &&
                            !order.getCompletionDate().isAfter(endTime))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error retrieving orders by time frame: " + e.getMessage());
            throw new OrdersSortException("An error occurred while retrieving orders by time frame. Please try again later.");
        }
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        if (comparators == null) {
            throw new OrdersSortException("Comparator list cannot be null.");
        }

        try {
            return comparators.stream()
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(() -> new OrdersSortException("Comparator list should not be empty."));
        } catch (Exception e) {
            System.err.println("Error combining comparators: " + e.getMessage());
            throw new OrdersSortException("An error occurred while combining comparators. Please try again later.");
        }
    }
}
