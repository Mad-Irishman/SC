package autoservice.repository;

import autoservice.models.order.Order;

import java.util.List;

public interface OrderRepository {
    boolean createOrder(Order order);

    List<Order> allOrders();

    boolean deleteOrder(Order order);
}
