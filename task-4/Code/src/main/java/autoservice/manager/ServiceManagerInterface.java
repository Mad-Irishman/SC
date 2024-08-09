package main.java.autoservice.manager;

import main.java.autoservice.models.GaragePlace;
import main.java.autoservice.models.Master;
import main.java.autoservice.models.order.Order;
import main.java.autoservice.models.order.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceManagerInterface {
    void addMaster(Master master);

    void removeMaster(Master master);

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(GaragePlace garagePlace);

    List<Master> getMastersByOrders(Order order);

    List<Master> getMastersSortedByAvailabilityAndName();

    List<GaragePlace> getAvailableGaragePlaces();

    void createOrder(String discription, Master assignedMaster, GaragePlace assignedGaragePlace, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price);

    List<Order> getOrdersByMaster(Master master);

    Order getOrderById(int id);

    void removeOrder(Order order);

    void completeOrder(Order order);

    void cancelOrder(Order order);

    void adjustOrdersForDelay(int orderId, int delayInHours);

    List<Order> sortListOrders();

    List<Order> getCurrentOrders();

    List<Order> getSortedCurrentOrders();

    List<Order> getOrdersByStatusAndTimeFrame(OrderStatus status, LocalDateTime startTime, LocalDateTime endTime);

    int getFreePlacesOnDate(LocalDateTime date);

    LocalDateTime getNearestFreeDate();

    void showAllOrders();

    void showAvailableMasters();

    void showAvailableGaragePlaces();

}