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
        if (master == null) {
            throw new GarageException("Мастер не может быть null");
        }
        this.masters.add(master);
    }

    public void removeMaster(Master master) {
        if (master == null) {
            throw new GarageException("Мастер не может быть null");
        }
        if (!this.masters.contains(master)) {
            throw new MasterNotFoundException("Мастер не найден в гараже");
        }
        this.masters.remove(master);
    }

    public List<Master> getAvailableMaster() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master : masters) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
    }

    public void addGaragePlace(GaragePlace place) {
        if (place == null) {
            throw new GarageException("Гаражное место не может быть null");
        }
        this.garagePlaces.add(place);
    }

    public void removeGaragePlace(GaragePlace place) {
        if (place == null) {
            throw new GarageException("Гаражное место не может быть null");
        }
        if (!this.garagePlaces.contains(place)) {
            throw new GaragePlaceNotFoundException("Гаражное место не найдено в гараже");
        }
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

    public List<GaragePlace> getGaragePlaces() {
        return garagePlaces;
    }
}
