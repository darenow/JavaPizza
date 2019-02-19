package eu.turuga.javapizza.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Data
@Builder
public class Pizza {
    private String name;

    private List<Ingredient> ingredients;

    private Integer quantity;

    private Double unitPrice;

    @Tolerate
    public Pizza(){

    }

}
