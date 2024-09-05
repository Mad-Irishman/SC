package autoservice.models.garage;

import autoservice.models.garage.exceptions.GarageException;
import autoservice.models.garage.exceptions.GaragePlaceNotFoundException;
import autoservice.models.garage.exceptions.MasterNotFoundException;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.garage.garageStatus.GarageStatue;

import java.util.List;
import java.util.ArrayList;

public class Garage {
    private final List<GaragePlace> garagePlaces;
    private final List<Master> masters;
    private GarageStatue isAvailable;

    public Garage() {
        this.garagePlaces = new ArrayList<>();
        this.masters = new ArrayList<>();
        this.isAvailable = GarageStatue.AVAILABLE;
    }

    public GarageStatue getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(GarageStatue isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void addMaster(Master master) {
        try {
            if (master == null) {
                throw new GarageException("Master cannot be null");
            }
            this.masters.add(master);
        } catch (GarageException e) {
            System.out.println("Error adding master: " + e.getMessage());
        }
    }

    public void removeMaster(Master master) {
        try {
            if (master == null) {
                throw new GarageException("Master cannot be null");
            }
            if (!this.masters.contains(master)) {
                throw new MasterNotFoundException("Master not found in garage");
            }
            this.masters.remove(master);
        } catch (GarageException e) {
            System.out.println("Error removing master: " + e.getMessage());
        }
    }

    public List<Master> getAvailableMasters() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master : masters) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
    }

    public List<Master> getAllMasters() {
        return new ArrayList<>(masters);
    }

    public void addGaragePlace(GaragePlace place) {
        try {
            if (place == null) {
                throw new GarageException("Garage place cannot be null");
            }
            this.garagePlaces.add(place);
        } catch (GarageException e) {
            System.out.println("Error adding garage place: " + e.getMessage());
        }
    }

    public void removeGaragePlace(GaragePlace place) {
        try {
            if (place == null) {
                throw new GarageException("Garage place cannot be null");
            }
            if (!this.garagePlaces.contains(place)) {
                throw new GaragePlaceNotFoundException("Garage place not found in garage");
            }
            this.garagePlaces.remove(place);
        } catch (GarageException e) {
            System.out.println("Error removing garage place: " + e.getMessage());
        }
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

    public List<GaragePlace> getGaragePlaces() {
        return new ArrayList<>(garagePlaces);
    }
}
