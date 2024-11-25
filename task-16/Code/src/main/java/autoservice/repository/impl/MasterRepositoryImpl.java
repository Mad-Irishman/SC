package autoservice.repository.impl;

import autoservice.database.connection.DatabaseConnection;
import autoservice.repository.MasterRepository;
import autoservice.models.master.Master;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MasterRepositoryImpl implements MasterRepository {
    private static final Logger logger = LoggerFactory.getLogger(MasterRepositoryImpl.class);

    @Override
    public boolean addMaster(Master master) {
        logger.info("Attempting to add master: {}", master);
        boolean isAdded = false;
        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(master);
                transaction.commit();
                isAdded = true;
                logger.info("Master added successfully: {}", master);
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error while adding master: {}", master, e);
            }
        } catch (Exception e) {
            logger.error("Error while opening session to add master: {}", master, e);
        }

        return isAdded;
    }


    @Override
    public List<Master> allMasters() {
        logger.info("Attempting to get all masters");
        try (Session session = DatabaseConnection.getInstance().getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Master> criteriaQuery = criteriaBuilder.createQuery(Master.class);
            Root<Master> root = criteriaQuery.from(Master.class);
            criteriaQuery.select(root);

            Query<Master> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error while fetching all masters", e);
        }
        return null;
    }


    @Override
    public boolean deleteMasterByName(Master master) {
        logger.info("Attempting to remove master: {}", master);
        boolean isDeleted = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Master> criteriaQuery = criteriaBuilder.createQuery(Master.class);
            Root<Master> root = criteriaQuery.from(Master.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), master.getName()));
            Master masterToDelete = session.createQuery(criteriaQuery).uniqueResult();

            if (masterToDelete != null) {
                session.delete(masterToDelete);
                transaction.commit();
                isDeleted = true;
                logger.info("Master with name '{}' was deleted successfully.", master);
            } else {
                logger.info("No master found with name '{}'.", master);
            }
        } catch (Exception e) {
            logger.error("Error while trying to delete master with name '{}'", master, e);
            isDeleted = false;
        }
        return isDeleted;
    }

    @Override
    public Master getMasterById(String id) {
        Master master = null;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
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
    public boolean updateMaster(Master master) {
        logger.info("Attempting to update master: {}", master);
        boolean isUpdated = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
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
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error updating master: {}", master, e);
            }
        } catch (Exception e) {
            logger.error("Error while accessing database to update master: {}", master, e);
            throw new RuntimeException(e);
        }
        return isUpdated;
    }
}
