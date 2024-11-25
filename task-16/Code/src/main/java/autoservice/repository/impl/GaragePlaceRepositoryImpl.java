package autoservice.repository.impl;

import autoservice.database.connection.DatabaseConnection;
import autoservice.repository.GaragePlaceRepository;
import autoservice.models.garagePlace.GaragePlace;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class GaragePlaceRepositoryImpl implements GaragePlaceRepository {
    private static final Logger logger = LoggerFactory.getLogger(GaragePlaceRepositoryImpl.class);

    @Override
    public boolean addGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to add garage_place: {}", garagePlace);

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(garagePlace);
                transaction.commit();

                logger.info("Successfully added garage_place: {}", garagePlace);
                return true;
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Failed to add garage_place: {}", garagePlace, e);
                return false;
            }
        } catch (HibernateException e) {
            logger.error("Error with Hibernate session while adding garage place: {}", garagePlace, e);
            return false;
        }
    }


    @Override
    public List<GaragePlace> getAllGaragePlaces() {
        logger.info("Attempting to get all garage places");
        try (Session session = DatabaseConnection.getInstance().getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GaragePlace> criteriaQuery = builder.createQuery(GaragePlace.class);
            Root<GaragePlace> root = criteriaQuery.from(GaragePlace.class);
            criteriaQuery.select(root);
            Query<GaragePlace> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error while fetching garage places", e);
        }
        return null;
    }

    @Override
    public boolean removeGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to remove garage place: {}", garagePlace);
        boolean isDeleted = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaDelete<GaragePlace> criteriaDelete = builder.createCriteriaDelete(GaragePlace.class);

                Root<GaragePlace> root = criteriaDelete.from(GaragePlace.class);
                criteriaDelete.where(builder.equal(root.get("placeNumber"), garagePlace.getPlaceNumber()));

                int rowsAffected = session.createQuery(criteriaDelete).executeUpdate();
                if (rowsAffected > 0) {
                    isDeleted = true;
                    logger.info("Successfully removed garage place: {}", garagePlace);
                } else {
                    logger.error("Failed to remove garage place: {}", garagePlace);
                }
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Failed to remove garage place: {}", garagePlace, e);
            }
        }
        return isDeleted;
    }

    @Override
    public boolean updateGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to update garage place: {}", garagePlace);
        boolean isUpdated = false;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaUpdate<GaragePlace> criteriaUpdate = builder.createCriteriaUpdate(GaragePlace.class);
                Root<GaragePlace> root = criteriaUpdate.from(GaragePlace.class);
                criteriaUpdate.set(root.get("isOccupied"), garagePlace.isOccupied());
                criteriaUpdate.where(builder.equal(root.get("placeNumber"), garagePlace.getPlaceNumber()));
                int rowsAffected = session.createQuery(criteriaUpdate).executeUpdate();
                if (rowsAffected > 0) {
                    isUpdated = true;
                    logger.info("Garage place updated successfully: {}", garagePlace);
                } else {
                    logger.info("Failed to update garage place: {}", garagePlace);
                }
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                    logger.info("Failed to update garage place: {}", garagePlace, e);
                }
            }
        } catch (Exception e) {
            logger.error("Error updating garage place: {}", garagePlace, e);
        }

        return isUpdated;
    }


    @Override
    public GaragePlace getGaragePlaceByNumber(int placeNumber) {
        logger.info("Attempting to get garage place by number: {}", placeNumber);
        GaragePlace garagePlace = null;

        try (Session session = DatabaseConnection.getInstance().getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GaragePlace> criteriaQuery = builder.createQuery(GaragePlace.class);
            Root<GaragePlace> root = criteriaQuery.from(GaragePlace.class);
            criteriaQuery.select(root).where(builder.equal(root.get("placeNumber"), placeNumber));
            Query<GaragePlace> query = session.createQuery(criteriaQuery);
            garagePlace = query.uniqueResult();
            if (garagePlace != null) {
                logger.info("Garage place found: {}", garagePlace);
            } else {
                logger.info("No garage place found with number: {}", placeNumber);
            }
        } catch (Exception e) {
            logger.error("Error while fetching garage place by number: {}", placeNumber, e);
        }
        return garagePlace;
    }


}
