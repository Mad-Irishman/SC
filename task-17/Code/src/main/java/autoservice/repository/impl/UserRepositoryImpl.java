package autoservice.repository.impl;

import autoservice.models.user.User;
import autoservice.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User findByName(String email) {
        logger.info("Attempting to retrieve user by username: {}", email);

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate namePredicate = builder.equal(root.get("email"), email);
            criteriaQuery.select(root).where(namePredicate);
            Query<User> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception e) {
            logger.error("Error while fetching user by username: {}", email, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        logger.info("Attempting to save user: {}", user);

        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
            logger.info("User saved successfully: {}", user);
            return user;
        } catch (HibernateException e){
            logger.error("Error while saving user: {}", user);
            throw new RuntimeException("Failed to save user", e);
        }
    }

}
