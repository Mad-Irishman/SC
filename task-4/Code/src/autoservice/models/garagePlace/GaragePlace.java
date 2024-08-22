package autoservice.models.garagePlace;


import autoservice.models.garagePlace.exception.GaragePlaceException;

public class GaragePlace {
    private int placeNumber;
    private boolean isOccupied = false;

    public GaragePlace(int placeNumber) {
        if (placeNumber <= 0) {
            throw new GaragePlaceException("Номер места должен быть положительным числом");
        }
        this.placeNumber = placeNumber;
    }

    public int getPlaceNumber() {
        return this.placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        if (placeNumber <= 0) {
            throw new GaragePlaceException("Номер места должен быть положительным числом");
        }
        this.placeNumber = placeNumber;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
