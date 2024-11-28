package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/master")
public class MasterRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public MasterRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping(path = "/allMasters")
    public List<Master> showAllMasters() {
        return serviceManager.getMasters();
    }

    @PostMapping(path = "/addMaster")
    public ResponseEntity<String> addMaster(@RequestBody Master newMaster) {
        boolean isAdded = serviceManager.addMaster(newMaster);
        if (isAdded) {
            return ResponseEntity.ok("Master added successfully");
        } else {
            return ResponseEntity.status(400).body("Master could not be added");
        }
    }

    @DeleteMapping("/deleteMaster/{id}")
    public ResponseEntity<String> deleteMaster(@PathVariable String id) {
        Master masterForDeleted = serviceManager.getMasterById(id);

        if (masterForDeleted == null) {
            return ResponseEntity.status(404).body("Master could not be found");
        } else {
            serviceManager.removeMaster(masterForDeleted);
            return ResponseEntity.ok("Master deleted successfully");
        }
    }

}
