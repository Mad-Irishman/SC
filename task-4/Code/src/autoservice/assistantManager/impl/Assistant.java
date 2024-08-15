package autoservice.assistantManager.impl;

import autoservice.assistantManager.AssistantInterface;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.impl.DataSort;
import autoservice.servicesSorting.GaragePlacesSort.impl.GaragePlacesSort;
import autoservice.servicesSorting.MastersSort.impl.MastersSort;
import autoservice.servicesSorting.OrdersSort.impl.OrdersSort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


public class Assistant implements AssistantInterface {
    private final MastersSort mastersSort;
    private final GaragePlacesSort garagePlacesSort;
    private final DataSort dataSort;
    private final OrdersSort ordersSort;

    public Assistant(MastersSort mastersSort, GaragePlacesSort garagePlacesSort, DataSort dataSort, OrdersSort ordersSort) {
        this.mastersSort = mastersSort;
        this.garagePlacesSort = garagePlacesSort;
        this.dataSort = dataSort;
        this.ordersSort = ordersSort;
    }

    @Override
    public List<Master> getMastersByOrders(Order order) {
        return mastersSort.getMastersByOrders(order);
    }

    @Override
    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        return mastersSort.getSortedMasters(comparators);
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        return garagePlacesSort.getAvailableGaragePlaces();
    }

    @Override
    public int getFreePlacesOnDate(LocalDateTime date) {
        return dataSort.getFreePlacesOnDate(date);
    }

    @Override
    public LocalDateTime getNearestFreeDate() {
        return dataSort.getNearestFreeDate();
    }

    @Override
    public List<Order> getSortedOrders(List<Comparator<Order>> comparators) {
        return ordersSort.getSortedOrders(comparators);
    }

    @Override
    public List<Order> getOrdersByMaster(Master master) {
        return ordersSort.getOrdersByMaster(master);
    }

    @Override
    public List<Order> getCurrentOrders() {
        return ordersSort.getCurrentOrders();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return ordersSort.getOrdersByStatus(status);
    }

    @Override
    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        return ordersSort.getOrdersByTimeFrame(orders, startTime, endTime);
    }
}
