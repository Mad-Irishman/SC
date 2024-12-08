package autoservice.controller;

import autoservice.dto.orderDTO.differentDTO.OrderDTOForGet;
import autoservice.dto.orderDTO.differentDTO.OrderDTOForPost;
import autoservice.dto.orderDTO.differentDTO.OrderDTOForResponse;
import autoservice.manager.impl.ServiceManager;
import autoservice.models.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderRestController {

    private final ServiceManager serviceManager;

    @Autowired
    public OrderRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTOForGet>> getOrders() {
        return ResponseEntity.status(200).body(serviceManager.getOrders());
    }

    //LocalDateTime have formate - ISO 8601 (yyyy-MM-dd'T'HH:mm:ss)
    @PostMapping
    public ResponseEntity<OrderDTOForResponse> postOrder(@RequestBody OrderDTOForPost orderDTOForPost) {
        String addedOrder = serviceManager.createOrder(orderDTOForPost.getDescription(), orderDTOForPost.getSubmissionDate(), orderDTOForPost.getCompletedDate(),
                orderDTOForPost.getPlannedStartDate(), orderDTOForPost.getPrice());
        OrderDTOForResponse orderDTOForResponse = new OrderDTOForResponse(addedOrder);
        return ResponseEntity.status(200).body(orderDTOForResponse);
    }

    // не выводится id
    @DeleteMapping(path = "/{description}")
    public ResponseEntity<OrderDTOForResponse> deleteOrder(@PathVariable String description) {
        Order order = serviceManager.getOrderByDescription(description);
        String orderForDelete = serviceManager.removeOrder(order);
        OrderDTOForResponse orderDTOForResponse = new OrderDTOForResponse(orderForDelete);
        return ResponseEntity.status(200).body(orderDTOForResponse);
    }


}
