package javaJunior.data.repository;

import javaJunior.data.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("select new Route(r.id, r.name, r.head) from Route r where r.id = :id")
    Route findByRouteId(@Param("id") int id);
}
