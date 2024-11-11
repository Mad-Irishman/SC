package autoservice.repository;

import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlaceRepository {
    boolean addGaragePlace(GaragePlace garagePlace);

    public List<GaragePlace> getAllGaragePlaces();

    public boolean removeGaragePlace(GaragePlace garagePlace);

    public boolean updateGaragePlace(GaragePlace garagePlace);

    public GaragePlace getGaragePlaceByNumber(int placeNumber);
}
