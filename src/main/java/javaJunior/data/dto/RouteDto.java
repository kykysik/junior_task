package javaJunior.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertFalse;
import java.util.List;

@Data
@Accessors(chain = true)
public class RouteDto {

    private int id;
    private String name;

    @AssertFalse
    private boolean head;
    private List<Double> locations;

    public RouteDto() {

    }

    public RouteDto(int id, String name, boolean head, List<Double> locations) {
        this.id = id;
        this.name = name;
        this.head = head;
        this.locations = locations;
    }
}
