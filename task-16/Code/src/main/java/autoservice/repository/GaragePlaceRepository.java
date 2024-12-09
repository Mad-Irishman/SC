package autoservice.repository;

import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlaceRepository {
    Integer addGaragePlace(GaragePlace garagePlace);

    List<GaragePlace> getAllGaragePlaces();

    Integer removeGaragePlace(GaragePlace garagePlace);

    boolean updateGaragePlace(GaragePlace garagePlace);

    GaragePlace getGaragePlaceByNumber(int placeNumber);
}
