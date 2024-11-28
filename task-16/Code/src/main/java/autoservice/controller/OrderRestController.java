package autoservice.controller;

import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public OrderRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping(path = "allOrders")
    public List<Order> getAllOrders() {
        return serviceManager.getAllOrdersInGarage();
    }



}
