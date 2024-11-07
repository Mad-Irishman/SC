package autoservice.models.garagePlace;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "garage_places")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GaragePlace {
    @Id
    @Column(name = "place_number")
    private int placeNumber;
    @Column(name = "is_occupied")
    private boolean isOccupied = false;

    public GaragePlace() {}

    public GaragePlace(int placeNumber, boolean isOccupied) {
        this.placeNumber = placeNumber;
        this.isOccupied = isOccupied;
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
}
