package autoservice.models.garagePlace;

public class GaragePlace {
    private int placeNumber;
    private boolean isOccupied = false;
    private String idOrder = null;

    public GaragePlace() {}

    public GaragePlace(int placeNumber, boolean isOccupied) {
        this.placeNumber = placeNumber;
        this.isOccupied = isOccupied;
    }

    public GaragePlace(int placeNumber, boolean isOccupied, String idOrder) {
        this.placeNumber = placeNumber;
        this.isOccupied = isOccupied;
        this.idOrder = idOrder;
    }

    public GaragePlace(int placeNumber) {
        this.placeNumber = placeNumber;

    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber){
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
