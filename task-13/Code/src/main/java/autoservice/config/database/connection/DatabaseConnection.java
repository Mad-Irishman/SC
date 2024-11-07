package autoservice.config.database.connection;

import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.order.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final SessionFactory sessionFactory;

    private DatabaseConnection() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(GaragePlace.class);
        configuration.addAnnotatedClass(Master.class);
        configuration.addAnnotatedClass(Order.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
