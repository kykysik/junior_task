package javaJunior.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class PlanDto {
    private List<RoutePlanDto> route = new ArrayList<>();
    private double total_distance;
}
