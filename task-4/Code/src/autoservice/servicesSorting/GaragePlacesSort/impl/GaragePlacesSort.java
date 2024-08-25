package autoservice.servicesSorting.GaragePlacesSort.impl;

import autoservice.models.garage.Garage;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.servicesSorting.GaragePlacesSort.GaragePlacesSortInterface;
import autoservice.servicesSorting.GaragePlacesSort.exception.GaragePlacesSortException;

import java.util.List;
import java.util.stream.Collectors;

public class GaragePlacesSort implements GaragePlacesSortInterface {


    @Override
    public List<GaragePlace> getAvailableGaragePlaces(List<Garage> garages) {
        try {
            List<GaragePlace> availableGaragePlaces = garages.stream().flatMap(garage -> {
                        if (garage == null) {
                            throw new GaragePlacesSortException("Garage data is not available.");
                        }
                        return garage.getAvailableGaragePlaces().stream();
                    })
                    .collect(Collectors.toList());
            if (availableGaragePlaces.isEmpty()) {
                throw new GaragePlacesSortException("No available garage places found. ");
            }
            return availableGaragePlaces;
        } catch (Exception e) {
            throw new GaragePlacesSortException("Error retrieving  available garage places: " + e.getMessage());
        }
    }
}
