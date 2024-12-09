package autoservice.controller;

import autoservice.dto.garagePlaceDTO.differentDTO.GaragePlaceDTOForGet;
import autoservice.dto.garagePlaceDTO.differentDTO.GaragePlaceDTOForPost;
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
    public ResponseEntity<List<GaragePlaceDTOForGet>> getGaragePlaces() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceManager.allGaragePlaces());
    }

    @PostMapping
    public ResponseEntity<GaragePlaceDTOForPost> postGaragePlace(@RequestBody GaragePlaceDTOForPost garagePlaceDTO) {
        Integer addedPlace = serviceManager.addGaragePlace(new GaragePlace(garagePlaceDTO.getId()));
        GaragePlaceDTOForPost responseDTO = new GaragePlaceDTOForPost(addedPlace);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GaragePlaceDTOForPost> deleteGaragePlace(@PathVariable("id") Integer id) {
        GaragePlace garagePlace = serviceManager.getGaragePlaceByNumber(id);
        Integer removedPlace = serviceManager.removeGaragePlace(garagePlace);
        GaragePlaceDTOForPost responseDTO = new GaragePlaceDTOForPost(removedPlace);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);


    }


}
