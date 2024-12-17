package autoservice.controller;

import autoservice.dto.masterDTO.differentDTO.MasterDTOForGet;
import autoservice.dto.masterDTO.differentDTO.MasterDTOForPost;
import autoservice.dto.masterDTO.differentDTO.MasterDTOForResponse;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/masters")
public class MasterRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public MasterRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping
    public ResponseEntity<List<MasterDTOForGet>> getMasters() {
        return ResponseEntity.status(200).body(serviceManager.getMasters());
    }

    @PostMapping
    public ResponseEntity<MasterDTOForResponse> postMaster(@RequestBody MasterDTOForPost masterDTOForPost) {
        String addedMaster = serviceManager.addMaster(new Master(masterDTOForPost.getName()));
        MasterDTOForResponse responseDTO = new MasterDTOForResponse(addedMaster);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<MasterDTOForResponse> deleteMaster(@PathVariable String name) {
        Master masterForDeleted = serviceManager.findMasterByName(name);
        String removeMaster = serviceManager.removeMaster(masterForDeleted);
        MasterDTOForResponse responseDTO = new MasterDTOForResponse(removeMaster);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
