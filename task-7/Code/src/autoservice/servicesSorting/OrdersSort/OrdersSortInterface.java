package autoservice.servicesSorting.OrdersSort;

import autoservice.models.master.Master;
import autoservice.models.order.Order;
import autoservice.models.order.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface OrdersSortInterface {

    List<Order> getSortedOrders(List<Order> orders, List<Comparator<Order>> comparators);

    List<Order> getOrdersByMaster(List<Order> orders, Master master);

    List<Order> getCurrentOrders(List<Order> orders);

    List<Order> getOrdersByStatus(List<Order> orders, OrderStatus status);

    List<Order> getOrdersByTimeFrame(List<Order> orders, LocalDateTime startTime, LocalDateTime endTime);
}
