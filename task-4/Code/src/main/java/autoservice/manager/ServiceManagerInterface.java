package main.java.autoservice.manager;

import main.java.autoservice.models.garage.Garage;
import main.java.autoservice.models.garage.essence.garagePlace.GaragePlace;
import main.java.autoservice.models.garage.essence.master.Master;
import main.java.autoservice.models.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceManagerInterface {
    void addMaster(Master master);

    void removeMaster(Master master);

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(GaragePlace garagePlace);

    List<Master> getMasters();

    List<Garage> getGarages();

    void createOrder(String discription, LocalDateTime submissionDate, LocalDateTime completionDate, LocalDateTime plannedStartDate, double price);

    List<Order> getOrders();

    Order getOrderById(int id);

    void removeOrder(Order order);

    void completeOrder(Order order);

    void cancelOrder(Order order);

    void adjustOrdersForDelay(int orderId, int delayInHours);

    void showAllOrders();

    void showAvailableMasters();

    void showAvailableGaragePlaces();

}