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
    void setOrders(List<Order> orders);

    void setMasters(List<Master> masters);

    String addMaster(Master master);

    String removeMaster(Master master);

    Integer addGaragePlace(GaragePlace garagePlace);

    Integer removeGaragePlace(GaragePlace garagePlace);

    Master getMasterById(String id);

    List<Master> getMasters();

    List<Master> getAllMasterInGarage();

    Master findMasterByName(String masterName);

    GaragePlace findGaragePlaceByNumber(String garagePlaceNumber);

    List<GaragePlace> allGaragePlaces();

    GaragePlace getGaragePlaceByNumber(int placeNumber);

    List<GaragePlace> getAvailableGaragePlaces();

    void createOrder(String discription, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price);

    void addOrder(Order order);

    List<Order> getOrders();

    List<Order> getAllOrdersInGarage();

    Order getOrderByDescription(String description);

    Order getOrderById(String id);

    void removeOrder(Order order);

    void completeOrder(Order order);

    void cancelOrder(Order order);

    void adjustOrdersForDelay(String orderId, int delayInHours);

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