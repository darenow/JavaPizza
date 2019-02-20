package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsPizzaDTO {
    private String name;
    private Integer quantity;
}
