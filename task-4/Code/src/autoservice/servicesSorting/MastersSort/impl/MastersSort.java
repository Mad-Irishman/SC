package autoservice.servicesSorting.MastersSort.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.servicesSorting.MastersSort.MastersSortInterface;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MastersSort implements MastersSortInterface {
    private final ServiceManager serviceManager;

    public MastersSort(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public List<Master> getMastersByOrders(Order order) {
        return serviceManager.getMasters().stream()
                .filter(master -> master.getOrdersMaster() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        return serviceManager.getMasters().stream()
                .sorted(combineComparators(comparators))
                .collect(Collectors.toList());
    }

    private <T> Comparator<T> combineComparators(List<Comparator<T>> comparators) {
        return comparators.stream()
                .reduce(Comparator::thenComparing)
                .orElseThrow(() -> new IllegalArgumentException("Список компараторов не должен быть пустым"));
    }
}
