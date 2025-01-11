package autoservice.dto.orderDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTOForGet {
    private String orderId;
    private String description;
    private Double price;

    public OrderDTOForGet() {
    }

    public OrderDTOForGet(String orderId, String description, Double price) {
        this.orderId = orderId;
        this.description = description;
        this.price = price;
    }
}
