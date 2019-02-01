package javaJunior.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@Accessors(chain = true)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double x;
    private double y;
    private boolean head;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public Location(){

    }

    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Location(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
