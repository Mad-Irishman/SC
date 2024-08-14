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
    private MastersSort mastersSort;
    private GaragePlacesSort garagePlacesSort;
    private DataSort dataSort;
    private OrdersSort ordersSort;

    public List<Master> getMastersByOrders(Order order) {
        return mastersSort.getMastersByOrders(order);
    }

    public List<Master> getSortedMasters(List<Comparator<Master>> comparators) {
        return mastersSort.getSortedMasters(comparators);
    }

    public List<GaragePlace> getAvailableGaragePlaces() {
        return garagePlacesSort.getAvailableGaragePlaces();
    }

    public int getFreePlacesOnDate(LocalDateTime date) {
        return dataSort.getFreePlacesOnDate(date);
    }

    public LocalDateTime getNearestFreeDate() {
        return dataSort.getNearestFreeDate();
    }

    public List<Order> getSortedOrders(List<Comparator<Order>> comparators) {
        return ordersSort.getSortedOrders(comparators);
    }

    public List<Order> getOrdersByMaster(Master master) {
        return ordersSort.getOrdersByMaster(master);
    }

    public List<Order> getCurrentOrders() {
        return ordersSort.getCurrentOrders();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return ordersSort.getOrdersByStatus(status);
    }

    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) {
        return ordersSort.getOrdersByTimeFrame(orders, startTime, endTime);
    }
}
