package javaJunior.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoutePlanDto {
    private int from;
    private int to;
    private double distance;
}
