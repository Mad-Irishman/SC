package autoservice.controller;

import autoservice.DTO.masterDTO.differentDTO.MasterDTO;
import autoservice.DTO.masterDTO.differentDTO.MasterDTO2;
import autoservice.DTO.masterDTO.mapper.MasterMapper;
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
    public ResponseEntity<List<MasterDTO>> showAllMasters() {
        List<Master> masters = serviceManager.getMasters();
        List<MasterDTO> masterDTOs = MasterMapper.toDTOList(masters);
        return ResponseEntity.status(200).body(masterDTOs);
    }

    @PostMapping
    public ResponseEntity<MasterDTO2> addMaster(@RequestBody MasterDTO2 masterDTO2) {
        Master master = new Master(masterDTO2.getId());

        try {
            String addedMaster = serviceManager.addMaster(master);
            MasterDTO2 responseDTO = new MasterDTO2(addedMaster);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // в терминале id, который есть в бд, но при этом он его не находит
    @DeleteMapping("/{name}")
    public ResponseEntity<MasterDTO2> deleteMaster(@PathVariable String name) {
        Master masterForDeleted = serviceManager.findMasterByName(name);

        try {
            String removeMaster = serviceManager.removeMaster(masterForDeleted);
            MasterDTO2 responseDTO = new MasterDTO2(removeMaster);
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
