package autoservice.dto.garagePlaceDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GaragePlaceDTOForGet {
    private Integer id;
    private boolean status;

    public GaragePlaceDTOForGet() {
    }

    public GaragePlaceDTOForGet(Integer id, boolean status) {
        this.id = id;
        this.status = status;
    }

}
