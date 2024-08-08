package autoservice.manager;

import autoservice.models.GaragePlace;
import autoservice.models.Master;
import autoservice.models.order.Order;

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

    void showAllOrders();

    void showAvailableMasters();

    void showAvailableGaragePlaces();

}