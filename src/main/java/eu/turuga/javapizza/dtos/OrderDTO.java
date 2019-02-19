package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    private Integer orderId;
    private List<PizzaDTO> pizzaList;
    private Double price;
}
