package autoservice.servicesSorting.GaragePlacesSort.impl;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.servicesSorting.GaragePlacesSort.GaragePlacesSortInterface;

import java.util.List;
import java.util.stream.Collectors;

public class GaragePlacesSort implements GaragePlacesSortInterface {
    private final ServiceManager serviceManager;

    public GaragePlacesSort(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        return serviceManager.getGarages().stream()
                .flatMap(garage -> garage.getAvailableGaragePlaces().stream())
                .collect(Collectors.toList());
    }
}
