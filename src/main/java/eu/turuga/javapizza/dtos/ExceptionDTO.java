package eu.turuga.javapizza.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private String error;
}
