package autoservice.models.garagePlace;

import autoservice.models.garagePlace.exception.GaragePlaceException;

public class GaragePlace {
    private int placeNumber;
    private boolean isOccupied = false;
    private String idOrder = null;


    public GaragePlace(int placeNumber) {
        if (placeNumber <= 0) {
            throw new GaragePlaceException("Place number must be a positive number");
        }
        this.placeNumber = placeNumber;

    }

    public int getPlaceNumber() {
        return this.placeNumber;
    }

    public void setPlaceNumber(int placeNumber) throws GaragePlaceException {
        if (placeNumber <= 0) {
            throw new GaragePlaceException("Place number must be a positive number");
        }
        this.placeNumber = placeNumber;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
}
