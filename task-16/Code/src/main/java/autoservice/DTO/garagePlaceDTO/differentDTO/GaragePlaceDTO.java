package autoservice.DTO.garagePlaceDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GaragePlaceDTO {
    private Integer id;

    public GaragePlaceDTO() {
    }

    public GaragePlaceDTO(Integer id) {
        this.id = id;
    }
}
