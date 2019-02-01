package javaJunior.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LocationDto {

    private int id;
    private String name;
    private double x;
    private double y;
}
