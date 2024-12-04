package autoservice.DTO.masterDTO.mapper;

import autoservice.DTO.masterDTO.differentDTO.MasterDTO;
import autoservice.models.master.Master;

import java.util.List;
import java.util.stream.Collectors;

public class MasterMapper {

    public static MasterDTO toDTO(Master master) {
        return new MasterDTO(master);
    }

    public static List<MasterDTO> toDTOList(List<Master> masters) {
        return masters.stream()
                .map(MasterMapper::toDTO)
                .collect(Collectors.toList());
    }
}
