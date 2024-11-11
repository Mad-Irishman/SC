package autoservice.repository;

import autoservice.models.order.Order;

import java.util.List;

public interface OrderRepository {
    public boolean createOrder(Order order);

    public List<Order> allOrders();

    public boolean deleteOrder(Order order);
}
