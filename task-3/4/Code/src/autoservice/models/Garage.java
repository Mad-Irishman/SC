package autoservice.models;

import java.util.List;
import java.util.ArrayList;

public class Garage {
    private final List<GaragePlace> garagePlaces;

    public Garage() {
        this.garagePlaces = new ArrayList<>();
    }

    public void addGaragePlace(GaragePlace place) {
        this.garagePlaces.add(place);
    }

    public void removeGaragePlace(GaragePlace place) {
        this.garagePlaces.remove(place);
    }

    public List<GaragePlace> getAvailableGaragePlaces() {
        List<GaragePlace> availablePlaces = new ArrayList<>();
        for (GaragePlace place : garagePlaces) {
            if (!place.isOccupied()) {
                availablePlaces.add(place);
            }
        }
        return availablePlaces;
    }
}