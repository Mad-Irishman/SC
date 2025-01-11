package autoservice.dto.orderDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTOForResponse {
    private String orderID;

    public OrderDTOForResponse() {
    }

    public OrderDTOForResponse(String orderID) {
        this.orderID = orderID;
    }
}
