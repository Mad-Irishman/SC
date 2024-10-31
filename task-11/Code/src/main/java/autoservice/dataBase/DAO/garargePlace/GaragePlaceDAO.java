package autoservice.dataBase.DAO.garargePlace;

import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlaceDAO {
    boolean addGaragePlace(GaragePlace garagePlace);

    public List<GaragePlace> getAllGaragePlaces();

    public boolean removeGaragePlace(GaragePlace garagePlace);
}
