package autoservice.repository.impl;

import autoservice.repository.GaragePlaceRepository;
import autoservice.models.garagePlace.GaragePlace;
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
public class GaragePlaceRepositoryImpl implements GaragePlaceRepository {
    private static final Logger logger = LoggerFactory.getLogger(GaragePlaceRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public GaragePlaceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Integer addGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to add garage_place: {}", garagePlace);

        try {
            Session session = sessionFactory.getCurrentSession();
            Integer id = (Integer) session.save(garagePlace);
            logger.info("Successfully added garage_place: {}", garagePlace);
            return id;
        } catch (HibernateException e) {
            logger.error("Failed to add garage_place: {}", garagePlace, e);
            throw new RuntimeException("Failed to add garage place", e);
        }
    }


    @Override
    @Transactional
    public List<GaragePlace> getAllGaragePlaces() {
        logger.info("Attempting to get all garage places");
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GaragePlace> criteriaQuery = builder.createQuery(GaragePlace.class);
            Root<GaragePlace> root = criteriaQuery.from(GaragePlace.class);
            criteriaQuery.select(root);
            Query<GaragePlace> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error("Error while fetching garage places", e);
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    @Transactional
    public Integer removeGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to remove garage place with ID: {}", garagePlace.getPlaceNumber());

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<GaragePlace> criteriaDelete = builder.createCriteriaDelete(GaragePlace.class);

            Root<GaragePlace> root = criteriaDelete.from(GaragePlace.class);
            criteriaDelete.where(builder.equal(root.get("placeNumber"), garagePlace.getPlaceNumber()));
            int rowsAffected = session.createQuery(criteriaDelete).executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Successfully removed garage place with ID: {}", garagePlace.getPlaceNumber());
                return garagePlace.getPlaceNumber();
            } else {
                logger.warn("Garage place with ID {} not found. Nothing was removed.", garagePlace.getPlaceNumber());
                throw new RuntimeException("Garage place with ID " + garagePlace.getPlaceNumber() + " not found");
            }
        } catch (HibernateException e) {
            logger.error("Error with Hibernate session while removing garage place with ID: {}", garagePlace.getPlaceNumber(), e);
            throw new RuntimeException("Database connection error", e);
        }
    }


    @Override
    @Transactional
    public boolean updateGaragePlace(GaragePlace garagePlace) {
        logger.info("Attempting to update garage place: {}", garagePlace);
        boolean isUpdated = false;

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<GaragePlace> criteriaUpdate = builder.createCriteriaUpdate(GaragePlace.class);
            Root<GaragePlace> root = criteriaUpdate.from(GaragePlace.class);
            criteriaUpdate.set(root.get("is_occupied"), garagePlace.isOccupied());
            criteriaUpdate.where(builder.equal(root.get("placeNumber"), garagePlace.getPlaceNumber()));
            int rowsAffected = session.createQuery(criteriaUpdate).executeUpdate();

            if (rowsAffected > 0) {
                isUpdated = true;
                logger.info("Garage place updated successfully: {}", garagePlace);
            } else {
                logger.info("Failed to update garage place: {}", garagePlace);
            }
        } catch (Exception e) {
            logger.error("Error updating garage place: {}", garagePlace, e);
        }

        return isUpdated;
    }


    @Override
    @Transactional
    public GaragePlace getGaragePlaceByNumber(int placeNumber) {
        logger.info("Attempting to get garage place by number: {}", placeNumber);
        GaragePlace garagePlace = null;

        try {
            Session session = sessionFactory.getCurrentSession();
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
