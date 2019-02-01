package javaJunior.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route")
@Data
@Accessors(chain = true)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private boolean head;

    @OneToMany(mappedBy = "route")
    private List<Location> locationsList = new ArrayList<>();

    public Route() {

    }

    public Route(int id, String name, boolean head) {
        this.id = id;
        this.head = head;
        this.name = name;
    }
}
