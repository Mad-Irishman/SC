package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.garagePlace.GaragePlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/garagePlace")
public class GaragePlaceRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public GaragePlaceRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping(path = "/allGaragePlaces")
    public List<GaragePlace> getAllGaragePlaces() {
        return serviceManager.allGaragePlaces();
    }

    @PostMapping(path = "/addGaragePlace")
    public ResponseEntity<GaragePlace> addGaragePlace(@RequestBody GaragePlace newGaragePlace) {
        boolean isAdded = serviceManager.addGaragePlace(newGaragePlace);
        if (isAdded) {
            return new ResponseEntity<>(newGaragePlace, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/deleteGaragePlace/{id}")
    public ResponseEntity<String> deleteGaragePlace(@PathVariable int id) {
        GaragePlace garagePlaceForDelete = serviceManager.getGaragePlaceByNumber(id);

        if (garagePlaceForDelete == null) {
            return ResponseEntity.status(404).body("Garage Place Not Found");
        } else {
            serviceManager.removeGaragePlace(garagePlaceForDelete);
            return new ResponseEntity<>("Garage Place Deleted Successfully", HttpStatus.OK);
        }
    }
}
