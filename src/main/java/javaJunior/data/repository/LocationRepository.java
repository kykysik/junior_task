package javaJunior.data.repository;

import javaJunior.data.entity.Location;
import javaJunior.data.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("select new Location(l.id, l.name, l.x, l.y) from Location l where l.id = ?1")
    Location findLocationById(int id);

    @Query("select new Location(l.x, l.y) from Location l where l.route = ?1")
    List<Location> findLocationRoteId(Route route);

    @Modifying
    @Query("delete from Location l where l.id = ?1")
    void deleteById(int id);

    @Modifying
    @Query("update Location l set l.name = ?1, l.y = ?2, l.x = ?3 where l.id = ?4")
    void updateById(String name, double x, double y, int id);
}
