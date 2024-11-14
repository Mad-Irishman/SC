package autoservice.repository.impl;

import autoservice.config.database.connection.DatabaseConnection;
import autoservice.repository.OrderRepository;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
//    private static final String CREATE_ORDER = "INSERT INTO orders (id_order, description, assigned_master, assigned_garage_place, submission_date, completion_date, planned_start_date, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String DELETE_ORDER = "DELETE FROM orders WHERE description = ?";
//    private static final String ALL_ORDERS = "SELECT * FROM orders";

    @Override
    public boolean createOrder(Order order) {
        logger.info("Attempting to create order: {}", order);
        boolean isCreated = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Master assignedMaster = order.getAssignedMaster();
                GaragePlace assignedGaragePlace = order.getAssignedGaragePlace();
                if (assignedMaster != null) {
                    session.saveOrUpdate(assignedMaster);
                }
                if (assignedGaragePlace != null) {
                    session.saveOrUpdate(assignedGaragePlace);
                }
                session.save(order);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.info("Failed to create order: {}", order);
            }
            logger.info("Order created successfully: {}", order);
            isCreated = true;
        } catch (Exception e) {
            logger.error("Error while creating order: {}", order, e);
        }
        return isCreated;
    }


    @Override
    public List<Order> allOrders() {
        logger.info("Attempting to retrieve all orders");

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            criteriaQuery.select(root);
            Query<Order> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error while fetching all orders", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deleteOrder(Order order) {
        logger.info("Attempting to delete order: {}", order);
        boolean isDeleted = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Order orderToDelete = session.get(Order.class, order.getIdOrder());
                if (orderToDelete != null) {
                    session.delete(orderToDelete);
                    transaction.commit();
                    isDeleted = true;
                    logger.info("Order with ID '{}' was deleted successfully.", order.getIdOrder());
                } else {
                    logger.info("Order with ID '{}' not found.", order.getIdOrder());
                }
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error while trying to delete order: {}", order.getIdOrder(), e);
            }
        } catch (Exception e) {
            logger.error("Error while opening session to delete order: {}", order.getIdOrder(), e);
        }
        return isDeleted;
    }

}
