package autoservice.repository.impl;

import autoservice.repository.OrderRepository;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public String createOrder(Order order) {
        logger.info("Attempting to create order: {}", order);
        try {
            Session session = sessionFactory.getCurrentSession();
            Master assignedMaster = order.getAssignedMaster();
            GaragePlace assignedGaragePlace = order.getAssignedGaragePlace();

            if (assignedMaster != null) {
                session.saveOrUpdate(assignedMaster);
            }
            if (assignedGaragePlace != null) {
                session.saveOrUpdate(assignedGaragePlace);
            }
            String orderId = (String) session.save(order);
            logger.info("Order created successfully: {}", order);
            return orderId;
        } catch (HibernateException e) {
            logger.error("Failed to create order: {}", order, e);
            throw new RuntimeException("Failed to create order", e);
        }
    }


    @Override
    public List<Order> allOrders() {
        logger.info("Attempting to retrieve all orders");

        try {
            Session session = sessionFactory.getCurrentSession();
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
    @Transactional
    public String deleteOrder(Order order) {
        logger.info("Attempting to delete order: {}", order);

        try {
            Session session = sessionFactory.getCurrentSession();
            Order orderToDelete = session.get(Order.class, order.getIdOrder());
            if (orderToDelete != null) {
                session.delete(orderToDelete);
                logger.info("Order with ID '{}' was deleted successfully.", order.getIdOrder());
                return order.getIdOrder();
            } else {
                logger.info("Order with ID '{}' not found.", order.getIdOrder());
                return null;
            }
        } catch (HibernateException e) {
            logger.error("Error while trying to delete order: {}", order.getIdOrder(), e);
            return null;
        }
    }
}
