package autoservice.servicesSorting.MastersSort.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.servicesSorting.MastersSort.MastersSortInterface;
import autoservice.servicesSorting.MastersSort.exception.MastersSortException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MastersSort implements MastersSortInterface {
    private final ServiceManager serviceManager;

    public MastersSort(ServiceManager serviceManager) {
        if (serviceManager == null) {
            throw new IllegalArgumentException("ServiceManager cannot be null.");
        }
        this.serviceManager = serviceManager;
    }

    @Override
    public List<Master> getMastersByOrders(Order order) {
        try {
            if (order == null) {
                throw new IllegalArgumentException("Order cannot be null.");
            }

            List<Master> mastersByOrder = serviceManager.getMasters().stream()
                    .filter(master -> order.contains(master))
                    .collect(Collectors.toList());

            if (mastersByOrder.isEmpty()) {
                throw new MastersSortException("No masters found for the specified order.");
            }

            return mastersByOrder;

        } catch (Exception e) {
            throw new MastersSortException("Error retrieving masters by orders: " + e.getMessage());
        }
    }


    @Override
    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        try {
            if (comparators == null || comparators.isEmpty()) {
                throw new IllegalArgumentException("Comparator list cannot be null or empty.");
            }

            return serviceManager.getMasters().stream()
                    .sorted(combineComparators(comparators))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new MastersSortException("Error sorting masters: " + e.getMessage());
        }
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        try {
            return comparators.stream()
                    .reduce(Comparator::thenComparing)
                    .orElseThrow(() -> new IllegalArgumentException("Comparator list should not be empty"));
        } catch (Exception e) {
            throw new MastersSortException("Error combining comparators: " + e.getMessage());
        }
    }
}