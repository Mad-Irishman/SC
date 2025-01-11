package autoservice.dto.orderDTO.mapper;

import autoservice.dto.orderDTO.differentDTO.OrderDTOForGet;
import autoservice.models.order.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTOForGet toDTO(Order order) {
        return new OrderDTOForGet(order.getIdOrder(), order.getDescription(),order.getPrice());
    }

    public static List<OrderDTOForGet> toDTOList(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
