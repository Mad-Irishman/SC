package autoservice.dto.orderDTO.differentDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTOForPost {
    private String description;
    private LocalDateTime submissionDate;
    private LocalDateTime completedDate;
    private LocalDateTime plannedStartDate;
    private Double price;

    public OrderDTOForPost() {
    }

    public OrderDTOForPost(LocalDateTime completedDate, String description, LocalDateTime plannedStartDate, Double price, LocalDateTime submissionDate) {
        this.completedDate = completedDate;
        this.description = description;
        this.plannedStartDate = plannedStartDate;
        this.price = price;
        this.submissionDate = submissionDate;
    }
}
