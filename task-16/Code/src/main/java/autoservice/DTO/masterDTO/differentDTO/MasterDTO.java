package autoservice.DTO.masterDTO.differentDTO;

import autoservice.models.master.Master;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterDTO {
    private String id;
    private String name;

    public MasterDTO() {}

    public MasterDTO(Master master) {
        this.id = master.getId();
        this.name = master.getName();
    }
}
