package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PizzaDTO {

    private String name;
    private String ingredients;
    private Integer quantity;
    private Double unitPrice;
}
