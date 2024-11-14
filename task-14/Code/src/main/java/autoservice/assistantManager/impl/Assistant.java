package autoservice.assistantManager.impl;

import autoservice.assistantManager.AssistantInterface;
import autoservice.exception.assistantException.exception.AssistantException;
import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;
import autoservice.servicesSorting.DataSort.impl.DataSort;
import autoservice.servicesSorting.GaragePlacesSort.impl.GaragePlacesSort;
import autoservice.servicesSorting.MastersSort.impl.MastersSort;
import autoservice.servicesSorting.OrdersSort.impl.OrdersSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class Assistant implements AssistantInterface {
    private final MastersSort mastersSort;
    private final GaragePlacesSort garagePlacesSort;
    private final DataSort dataSort;
    private final OrdersSort ordersSort;

    @Autowired
    public Assistant(MastersSort mastersSort, GaragePlacesSort garagePlacesSort, DataSort dataSort, OrdersSort ordersSort) {
        this.mastersSort = mastersSort;
        this.garagePlacesSort = garagePlacesSort;
        this.dataSort = dataSort;
        this.ordersSort = ordersSort;
    }

    @Override
    public List<Master> getMastersByOrders(List<Master> masters, Order order) throws AssistantException {
        if (order == null) {
            System.err.println("Error: Order cannot be null.");
            return masters;
        }
        try {
            return mastersSort.getMastersByOrders(masters, order);
        } catch (Exception e) {
            System.err.println("Error retrieving masters by order. Please try again later.");
            return masters;
        }
    }

    @Override
    public List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators) throws AssistantException {
        if (comparators == null) {
            System.err.println("Error: Comparators list cannot be null.");
            return masters;
        }
        try {
            return mastersSort.getSortedMasters(masters, comparators);
        } catch (Exception e) {
            System.err.println("Error sorting masters. Please try again later.");
            return masters;
        }
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) throws AssistantException {
        try {
            return garagePlacesSort.getAvailableGaragePlaces(garages);
        } catch (Exception e) {
            System.err.println("Error retrieving available garage places. Please try again later.");
            return null;
        }
    }

    @Override
    public int getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date) throws AssistantException {
        if (date == null) {
            System.err.println("Error: Date cannot be null.");
            return -1;
        }
        try {
            return dataSort.getFreePlacesOnDate(orders, masters, garages, date);
        } catch (Exception e) {
            System.err.println("Error retrieving free places on the specified date. Please try again later.");
            return -1;
        }
    }

    @Override
    public LocalDateTime getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages) throws AssistantException {
        try {
            return dataSort.getNearestFreeDate(masters, orders, garages);
        } catch (Exception e) {
            System.err.println("Error retrieving nearest free date. Please try again later.");
            return null;
        }
    }

    @Override
    public List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators) throws AssistantException {
        if (comparators == null) {
            System.err.println("Error: Comparators list cannot be null.");
            return orders;
        }
        try {
            return ordersSort.getSortedOrders(orders, comparators);
        } catch (Exception e) {
            System.err.println("Error sorting orders. Please try again later.");
            return orders;
        }
    }

    @Override
    public List<Order> getOrdersByMaster(List<Order> orders, Master master) throws AssistantException {
        if (master == null) {
            System.err.println("Error: Master cannot be null.");
            return orders;
        }
        try {
            return ordersSort.getOrdersByMaster(orders, master);
        } catch (Exception e) {
            System.err.println("Error retrieving orders by master. Please try again later.");
            return orders;
        }
    }

    @Override
    public List<Order> getCurrentOrders(List<Order> orders) throws AssistantException {
        try {
            return ordersSort.getCurrentOrders(orders);
        } catch (Exception e) {
            System.err.println("Error retrieving current orders. Please try again later.");
            return orders;
        }
    }

    @Override
    public List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status) throws AssistantException {
        if (status == null) {
            System.err.println("Error: Order status cannot be null.");
            return orders;
        }
        try {
            return ordersSort.getOrdersByStatus(orders, status);
        } catch (Exception e) {
            System.err.println("Error retrieving orders by status. Please try again later.");
            return orders;
        }
    }

    @Override
    public List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime) throws AssistantException {
        if (orders == null || startTime == null || endTime == null) {
            System.err.println("Error: Parameters cannot be null.");
            return orders;
        }
        if (startTime.isAfter(endTime)) {
            System.err.println("Error: Start time cannot be after end time.");
            return orders;
        }
        try {
            return ordersSort.getOrdersByTimeFrame(orders, startTime, endTime);
        } catch (Exception e) {
            System.err.println("Error retrieving orders by time frame. Please try again later.");
            return orders;
        }
    }
}