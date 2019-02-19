package eu.turuga.javapizza.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {

    private Integer orderId;

    private List<Pizza> pizzaList;

    private Double price;
}
