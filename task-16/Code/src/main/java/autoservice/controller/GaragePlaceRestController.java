package autoservice.controller;

import autoservice.DTO.garagePlaceDTO.differentDTO.GaragePlaceDTO2;
import autoservice.DTO.garagePlaceDTO.differentDTO.GaragePlaceDTO;
import autoservice.DTO.garagePlaceDTO.mapper.GaragePlaceMapper;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/garage-places")
public class GaragePlaceRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public GaragePlaceRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping
    public ResponseEntity<List<GaragePlaceDTO2>> getAllGaragePlaces() {
        List<GaragePlace> garagePlaces = serviceManager.allGaragePlaces();
        List<GaragePlaceDTO2> garagePlaceDTOs = GaragePlaceMapper.toDTOList(garagePlaces);
        return ResponseEntity.status(HttpStatus.OK).body(garagePlaceDTOs);
    }

    @PostMapping
    public ResponseEntity<GaragePlaceDTO> addGaragePlace(@RequestBody GaragePlaceDTO garagePlaceDTO) {
        GaragePlace garagePlace = new GaragePlace(garagePlaceDTO.getId());

        try {
            Integer addedPlace = serviceManager.addGaragePlace(garagePlace);
            GaragePlaceDTO responseDTO = new GaragePlaceDTO(addedPlace);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    // постоянно ошибка 400
    @DeleteMapping("/{id}")
    public ResponseEntity<GaragePlaceDTO> removeGaragePlace(@PathVariable("id") Integer id) {
        GaragePlace garagePlace = serviceManager.getGaragePlaceByNumber(id);

        try {
            Integer removedPlace = serviceManager.removeGaragePlace(garagePlace);
            GaragePlaceDTO responseDTO = new GaragePlaceDTO(removedPlace);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }



}
