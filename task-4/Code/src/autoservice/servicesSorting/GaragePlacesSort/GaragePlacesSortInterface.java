package autoservice.servicesSorting.GaragePlacesSort;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;

import java.util.List;

public interface GaragePlacesSortInterface {

    List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages);
}
