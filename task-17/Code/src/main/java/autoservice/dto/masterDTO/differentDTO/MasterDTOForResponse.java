package autoservice.dto.masterDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDTOForResponse {
    private String id;

    public MasterDTOForResponse() {
    }

    public MasterDTOForResponse(String id) {
        this.id = id;
    }
}
