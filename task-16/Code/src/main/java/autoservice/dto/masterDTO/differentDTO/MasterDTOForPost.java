package autoservice.dto.masterDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDTOForPost {
    private String name;

    public MasterDTOForPost() {}

    public MasterDTOForPost(String name) {
        this.name = name;
    }
}
