package autoservice.DTO.garagePlaceDTO.mapper;

import autoservice.DTO.garagePlaceDTO.differentDTO.GaragePlaceDTO2;
import autoservice.models.garagePlace.GaragePlace;

import java.util.List;
import java.util.stream.Collectors;

public class GaragePlaceMapper {

    public static GaragePlaceDTO2 toDTO(GaragePlace garagePlace) {
        return new GaragePlaceDTO2(garagePlace);
    }

    public static List<GaragePlaceDTO2> toDTOList(List<GaragePlace> garagePlaces) {
        return garagePlaces.stream()
                .map(GaragePlaceMapper::toDTO)
                .collect(Collectors.toList());
    }
}

