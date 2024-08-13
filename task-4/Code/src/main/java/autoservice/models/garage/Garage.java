package main.java.autoservice.models.garage;

import main.java.autoservice.models.garage.essence.garagePlace.GaragePlace;
import main.java.autoservice.models.garage.essence.master.Master;
import main.java.autoservice.models.garage.essence.master.masterStatus.MasterStatus;
import main.java.autoservice.models.garage.garageStatus.GarageStatue;

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
        this.masters.add(master);
    }

    public void removeMaster(Master master) {
        this.masters.remove(master);
    }


    public List<Master> getAvailableMaster() {
        List<Master> availableMasters = new ArrayList<>();
        for (Master master: masters) {
            if (master.isAvailable() == MasterStatus.AVAILABLE) {
                availableMasters.add(master);
            }
        }
        return availableMasters;
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

    public List<GaragePlace> getGaragePlaces() {
        return garagePlaces;
    }

}