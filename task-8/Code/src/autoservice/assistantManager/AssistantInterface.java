package autoservice.assistantManager;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface AssistantInterface {

    List<Master> getMastersByOrders(List<Master> masters, Order order);

    List<Master> getSortedMasters(List<Master> masters, List<Comparator<Master>> comparators);

    List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages);

    int getFreePlacesOnDate(List<Order> orders, List<Master> masters, List<Garage> garages, LocalDateTime date);

    LocalDateTime getNearestFreeDate(List<Master> masters, List<Order> orders, List<Garage> garages);

    List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators);

    List<Order> getOrdersByMaster(List<Order> orders, Master master);

    List<Order> getCurrentOrders(List<Order> orders);

    List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status);

    List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime);
}
