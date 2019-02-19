package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuDTO {
    private String name;
    private String ingredients;
    private Double price;

}
