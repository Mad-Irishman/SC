package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.master.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public MyRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/masters")
    public List<Master> showAllMasters() {
        return serviceManager.getMasters();
    }

}
