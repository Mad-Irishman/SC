package autoservice.DTO.garagePlaceDTO.differentDTO;

import autoservice.models.garagePlace.GaragePlace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GaragePlaceDTO2 {
    private Integer id;
    private boolean status;

    public GaragePlaceDTO2() {
    }

    public GaragePlaceDTO2(GaragePlace garagePlace) {
        this.id = garagePlace.getPlaceNumber();
        this.status = garagePlace.isOccupied();
    }

}
