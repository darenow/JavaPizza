package eu.turuga.javapizza.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

    private String name;

    private Double price;
}
