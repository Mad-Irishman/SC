package autoservice.dto.garagePlaceDTO.mapper;

import autoservice.dto.garagePlaceDTO.differentDTO.GaragePlaceDTOForGet;
import autoservice.models.garagePlace.GaragePlace;

import java.util.List;
import java.util.stream.Collectors;

public class GaragePlaceMapper {

    public static GaragePlaceDTOForGet toDTO(GaragePlace garagePlace) {
        return new GaragePlaceDTOForGet(garagePlace.getPlaceNumber(), garagePlace.isOccupied());
    }

    public static List<GaragePlaceDTOForGet> toDTOList(List<GaragePlace> garagePlaces) {
        return garagePlaces.stream()
                .map(GaragePlaceMapper::toDTO)
                .collect(Collectors.toList());
    }
}

