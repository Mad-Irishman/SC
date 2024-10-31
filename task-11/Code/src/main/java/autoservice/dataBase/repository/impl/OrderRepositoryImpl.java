package autoservice.dataBase.repository.impl;

import autoservice.config.database.connection.DatabaseConnection;
import autoservice.dataBase.repository.OrderRepository;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    private static final String CREATE_ORDER = "INSERT INTO orders (id_order, description, assigned_master, assigned_garage_place, submission_date, completion_date, planned_start_date, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE description = ?";
    private static final String ALL_ORDERS = "SELECT * FROM orders";

    @Override
    public boolean createOrder(Order order) {
        logger.info("Attempting to create order: {}", order);

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER)) {

            preparedStatement.setString(1, order.getIdOrder());
            preparedStatement.setString(2, order.getDescription());

            Master assignedMaster = order.getAssignedMaster();
            preparedStatement.setString(3, assignedMaster.getName());

            GaragePlace assignedGaragePlace = order.getAssignedGaragePlace();
            preparedStatement.setInt(4, assignedGaragePlace.getPlaceNumber());


            preparedStatement.setTimestamp(5, Timestamp.valueOf(order.getSubmissionDate()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(order.getCompletionDate()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(order.getPlannedStartDate()));

            preparedStatement.setDouble(8, order.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            logger.info("Order created successfully, {} rows affected", rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> allOrders() {
        logger.info("Attempting to all orders");
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Order order = new Order();
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }


    @Override
    public boolean deleteOrder(Order order) {
        logger.info("Attempting to delete order: {}", order);
        boolean isDeleted = false;

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setString(1, order.getDescription());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
                logger.info("Order with name '{}' was deleted successfully.", order.getDescription());
            } else {
                logger.info("Order with name '{}' was not deleted successfully.", order.getDescription());
            }
        } catch (SQLException e) {
           logger.error("Error while trying to delete order: {}", order.getDescription(), e);
        }
        return isDeleted;
    }
}
