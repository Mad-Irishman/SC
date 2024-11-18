package autoservice.repository;

import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlaceRepository {
    boolean addGaragePlace(GaragePlace garagePlace);

    List<GaragePlace> getAllGaragePlaces();

    boolean removeGaragePlace(GaragePlace garagePlace);

    boolean updateGaragePlace(GaragePlace garagePlace);

    GaragePlace getGaragePlaceByNumber(int placeNumber);
}
