package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/master")
public class MasterRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public MasterRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/allMasters")
    public List<Master> showAllMasters() {
        return serviceManager.getMasters();
    }



}
