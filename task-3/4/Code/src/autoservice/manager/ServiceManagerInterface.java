package autoservice.manager;

import autoservice.models.GaragePlace;
import autoservice.models.Master;
import autoservice.models.order.Order;

import java.time.LocalDateTime;

public interface ServiceManagerInterface {
    void addMaster(Master master);

    void removeMaster(Master master);

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(GaragePlace garagePlace);

    void createOrder(String description, Master master, GaragePlace place, LocalDateTime startTime, int durationInHours);

    Order getOrderById(int id);

    void removeOrder(Order order);

    void completeOrder(Order order);

    void cancelOrder(Order order);

    void adjustOrdersForDelay(int orderId, int delayInHours);

    void showAllOrders();

    void showAvailableMasters();

    void showAvailableGaragePlaces();

}