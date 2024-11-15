package autoservice.manager;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface ServiceManagerInterface {
    void addMaster(Master master);

    void removeMaster(Master master);

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(GaragePlace garagePlace);

    List<Master> getMasters();

    List<Master> getAllMasterInGarage();

    List<GaragePlace> allGaragePlaces();

    GaragePlace getGaragePlaceByNumber(int placeNumber);

    List<Garage> getGarages();

    void createOrder(String discription, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price);

    List<Order> getOrders();

    Order getOrderByDescription(String description);

    Order getOrderById(int id);

    void removeOrder(Order order);

    void completeOrder(Order order);

    void cancelOrder(Order order);

    void adjustOrdersForDelay(int orderId, int delayInHours);

    void showAllOrders();

    void showAvailableMasters();

    void showAvailableGaragePlaces();

    List<Master> getMastersByOrders(List<Master> masters, Order order);

    List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators);

    List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages);

    void getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date);

    void getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages);

    List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators);

    void getOrdersByMaster(List<Order> orders, Master master);

    List<Order> getCurrentOrders(List<Order> orders);

    void getOrdersByStatus(List<Order> orders, OrderStatus status);

    void getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime);
}