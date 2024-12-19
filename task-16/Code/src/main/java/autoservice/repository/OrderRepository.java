package autoservice.repository;

import autoservice.models.order.Order;

import java.util.List;

public interface OrderRepository {
    String createOrder(Order order);

    List<Order> allOrders();

    String deleteOrder(Order order);
}
