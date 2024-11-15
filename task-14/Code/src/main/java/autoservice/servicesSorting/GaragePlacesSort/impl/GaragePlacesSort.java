package autoservice.servicesSorting.GaragePlacesSort.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.servicesSorting.GaragePlacesSort.GaragePlacesSortInterface;
import autoservice.exception.garagePlaceSortException.GaragePlacesSortException;
import autoservice.service.impl.GarageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaragePlacesSort implements GaragePlacesSortInterface {

    @Autowired
    private GarageServiceImpl garageService;

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) throws GaragePlacesSortException {
        if (garages == null || garages.isEmpty()) {
            throw new GaragePlacesSortException("Garages list cannot be null or empty.");
        }

        try {
            List<GaragePlace> availableGaragePlaces = garageService.getAvailableGaragePlaces();

            if (availableGaragePlaces.isEmpty()) {
                throw new GaragePlacesSortException("No available garage places found.");
            }

            return availableGaragePlaces;
        } catch (Exception e) {
            throw new GaragePlacesSortException("Error retrieving available garage places: " + e.getMessage());
        }
    }
}
