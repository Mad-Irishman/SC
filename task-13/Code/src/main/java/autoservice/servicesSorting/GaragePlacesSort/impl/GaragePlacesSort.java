package autoservice.servicesSorting.GaragePlacesSort.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.servicesSorting.GaragePlacesSort.GaragePlacesSortInterface;
import autoservice.exception.garagePlaceSortException.GaragePlacesSortException;

import java.util.List;
import java.util.stream.Collectors;

public class GaragePlacesSort implements GaragePlacesSortInterface {

    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) throws GaragePlacesSortException {
        try {
            if (garages == null) {
                throw new GaragePlacesSortException("Garages list cannot be null.");
            }

            List<GaragePlace> availableGaragePlaces = garages.stream()
                    .flatMap(garage -> {
                        if (garage == null) {
                            throw new GaragePlacesSortException("One of the garages is null.");
                        }
                        return garage.getAvailableGaragePlaces().stream();
                    })
                    .collect(Collectors.toList());

            if (availableGaragePlaces.isEmpty()) {
                throw new GaragePlacesSortException("No available garage places found.");
            }

            return availableGaragePlaces;
        } catch (GaragePlacesSortException e) {
            System.err.println("Error in getAvailableGaragePlaces: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new GaragePlacesSortException("Error retrieving available garage places: " + e.getMessage());
        }
    }
}
