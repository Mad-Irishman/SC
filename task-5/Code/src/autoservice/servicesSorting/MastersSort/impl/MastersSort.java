package autoservice.servicesSorting.MastersSort.impl;

import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.servicesSorting.MastersSort.MastersSortInterface;
import autoservice.servicesSorting.MastersSort.exception.MastersSortException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MastersSort implements MastersSortInterface {

    @Override
    public List<Master> getMastersByOrders(List<Master> masters, Order order) {
        try {
            if (order == null) {
                throw new IllegalArgumentException("Order cannot be null.");
            }

            List<Master> mastersByOrder = masters.stream()
                    .filter(master -> order.contains(master))
                    .collect(Collectors.toList());

            if (mastersByOrder.isEmpty()) {
                throw new MastersSortException("No masters found for the specified order.");
            }

            return mastersByOrder;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            throw new MastersSortException("Error retrieving masters by orders: " + e.getMessage());
        } catch (Exception e) {
            throw new MastersSortException("Error retrieving masters by orders: " + e.getMessage());
        }
    }

    @Override
    public List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators) {
        try {
            if (comparators == null || comparators.isEmpty()) {
                throw new IllegalArgumentException("Comparator list cannot be null or empty.");
            }

            return masters.stream()
                    .sorted(combineComparators(comparators))
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            throw new MastersSortException("Error sorting masters: " + e.getMessage());
        } catch (Exception e) {
            throw new MastersSortException("Error sorting masters: " + e.getMessage());
        }
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        try {
            return comparators.stream()
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(() -> new IllegalArgumentException("Comparator list should not be empty"));

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            throw new MastersSortException("Error combining comparators: " + e.getMessage());
        } catch (Exception e) {
            throw new MastersSortException("Error combining comparators: " + e.getMessage());
        }
    }
}
