package autoservice.dataBase.repository;

import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlaceRepository {
    boolean addGaragePlace(GaragePlace garagePlace);

    public List<GaragePlace> getAllGaragePlaces();

    public boolean removeGaragePlace(GaragePlace garagePlace);
}
