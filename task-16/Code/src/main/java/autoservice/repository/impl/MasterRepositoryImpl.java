package autoservice.repository.impl;

import autoservice.repository.MasterRepository;
import autoservice.models.master.Master;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.criteria.*;
import java.util.List;

@Repository
public class MasterRepositoryImpl implements MasterRepository {
    private static final Logger logger = LoggerFactory.getLogger(MasterRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public MasterRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public String addMaster(Master master) {
        logger.info("Attempting to add master: {}", master);

        try {
            Session session = sessionFactory.getCurrentSession();
            String id = (String) session.save(master);
            logger.info("Master added successfully: {}", master);
            return id;
        } catch (HibernateException e) {
            logger.error("Error while adding master: {}", master, e);
            throw new RuntimeException("Failed to add master", e);
        }
    }


    @Override
    public List<Master> allMasters() {
        logger.info("Attempting to get all masters");
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Master> criteriaQuery = criteriaBuilder.createQuery(Master.class);
            Root<Master> root = criteriaQuery.from(Master.class);
            criteriaQuery.select(root);

            Query<Master> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error("Error while fetching all masters", e);
            throw new RuntimeException("Database connection error", e);
        }
    }


    @Override
    @Transactional
    public String removeMasterByName(Master master) {
        logger.info("Attempting to remove master with ID: {}", master.getId());

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Master> criteriaDelete = criteriaBuilder.createCriteriaDelete(Master.class);

            Root<Master> root = criteriaDelete.from(Master.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("name"), master.getId()));

            int rowsAffected = session.createQuery(criteriaDelete).executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Successfully removed master with ID: {}", master.getId());
                return master.getId();
            } else {
                logger.warn("Master with ID '{}' not found. Nothing was removed.", master.getId());
                throw new RuntimeException("Master with ID " + master.getId() + " not found");
            }
        } catch (HibernateException e) {
            logger.error("Failed to remove master with ID: {}", master.getId(), e);
            throw new RuntimeException("Failed to remove master", e);
        }
    }


    @Override
    public Master getMasterById(String id) {
        Master master = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Master> criteriaQuery = criteriaBuilder.createQuery(Master.class);
            Root<Master> root = criteriaQuery.from(Master.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            master = session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            logger.error("Error while trying to retrieve master with ID '{}'", id, e);
        }
        return master;
    }


    @Override
    @Transactional
    public boolean updateMaster(Master master) {
        logger.info("Attempting to update master: {}", master);
        boolean isUpdated = false;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Master> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Master.class);
            Root<Master> root = criteriaUpdate.from(Master.class);
            criteriaUpdate.set(root.get("isAvailable"), master.getAvailable());
            criteriaUpdate.where(criteriaBuilder.equal(root.get("name"), master.getName()));
            int rowsAffected = session.createQuery(criteriaUpdate).executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
                logger.info("Master updated successfully: {}", master);
            } else {
                logger.info("Failed to update master: {}", master);
            }

        } catch (Exception e) {
            logger.error("Error while accessing database to update master: {}", master, e);
            throw new RuntimeException(e);
        }
        return isUpdated;
    }
}
