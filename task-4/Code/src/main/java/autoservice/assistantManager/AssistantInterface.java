package main.java.autoservice.assistantManager;

import main.java.autoservice.models.garage.essence.garagePlace.GaragePlace;
import main.java.autoservice.models.garage.essence.master.Master;
import main.java.autoservice.models.order.Order;
import main.java.autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface AssistantInterface {
    List<Master> getMastersByOrders(Order order);

    List<Master> getSortedMasters(List<Comparator<Master>> comparators);

    List<Order> getSortedOrders(List<Comparator<Order>> comparators);

    List<GaragePlace> getAvailableGaragePlaces();

    List<Order> getOrdersByMaster(Master master);

    List<Order> getCurrentOrders();

    List<Order> getOrdersByStatusAndTimeFrame(OrderStatus status, LocalDateTime startTime, LocalDateTime endTime);

    int getFreePlacesOnDate(LocalDateTime date);

    LocalDateTime getNearestFreeDate();
}
