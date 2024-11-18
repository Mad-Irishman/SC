package autoservice.models.garagePlace;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "garage_places")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GaragePlace {
    @Id
    @Column(name = "place_number")
    private int placeNumber;
    @Setter
    @Column(name = "is_occupied")
    private boolean occupied = false;

    public GaragePlace() {}

    public GaragePlace(int placeNumber, boolean occupied) {
        this.placeNumber = placeNumber;
        this.occupied = occupied;
    }

    public GaragePlace(int placeNumber) {
        this.placeNumber = placeNumber;

    }

}
