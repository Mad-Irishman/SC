package autoservice.dto.garagePlaceDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GaragePlaceDTOForPost {
    private Integer id;

    public GaragePlaceDTOForPost() {
    }

    public GaragePlaceDTOForPost(Integer id) {
        this.id = id;
    }
}
