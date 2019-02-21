package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsOrderDTO {
    private String info;
    private Double value;
}
