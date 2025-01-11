package autoservice.dto.masterDTO.mapper;

import autoservice.dto.masterDTO.differentDTO.MasterDTOForGet;
import autoservice.models.master.Master;

import java.util.List;
import java.util.stream.Collectors;

public class MasterMapper {

    public static MasterDTOForGet toDTO(Master master) {
        return new MasterDTOForGet(master.getId(), master.getName());
    }

    public static List<MasterDTOForGet> toDTOList(List<Master> masters) {
        return masters.stream()
                .map(MasterMapper::toDTO)
                .collect(Collectors.toList());
    }
}
