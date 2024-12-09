package autoservice.dto.masterDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDTOForGet {
    private String id;
    private String name;

    public MasterDTOForGet() {}

    public MasterDTOForGet(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
