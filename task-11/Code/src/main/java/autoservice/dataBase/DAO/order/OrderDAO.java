package autoservice.dataBase.DAO.order;

import autoservice.models.order.Order;

import java.util.List;

public interface OrderDAO {
    public boolean createOrder(Order order);

    public List<Order> allOrders();

    public boolean deleteOrder(Order order);
}
