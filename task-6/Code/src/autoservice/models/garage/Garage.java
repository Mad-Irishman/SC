package autoservice.models.garage;

import autoservice.models.garage.exceptions.GarageException;
import autoservice.models.garage.exceptions.GaragePlaceNotFoundException;
import autoservice.models.garage.exceptions.MasterNotFoundException;
import autoservice.models.garagePlace.GaragePlace;
import autoservice.models.master.Master;
import autoservice.models.master.masterStatus.MasterStatus;
import autoservice.models.garage.garageStatus.GarageStatus;
import autoservice.models.order.Order;

import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

public class Garage {
    private final String id;
    private final List<GaragePlace> garagePlaces;
    private final List<Master> masters;
    private final List<Order> orders;
    private GarageStatus isAvailable;

    public Garage() {
        this.id = generateUniqueId();
        this.garagePlaces = new ArrayList<>();
        this.masters = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.isAvailable = GarageStatus.AVAILABLE;
    }

    public GarageStatus getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(GarageStatus isAvailable) {
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

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public String getId() {
        return this.id;
    }

    private static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
