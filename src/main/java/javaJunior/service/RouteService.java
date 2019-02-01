package javaJunior.service;

import javaJunior.data.dto.PlanDto;
import javaJunior.data.dto.RouteDto;
import javaJunior.data.dto.RoutePlanDto;
import javaJunior.data.entity.Location;
import javaJunior.data.entity.Route;
import javaJunior.data.repository.LocationRepository;
import javaJunior.data.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository repository;
    @Autowired
    private LocationRepository locationRepository;

    public RouteDto create(RouteDto routeDto) {
        List<Double> locations = routeDto.getLocations();
        List<Location> locationList = new ArrayList<>();

        for(int i = 0; i < locations.size(); i+=2) {
            locationList.add(new Location()
                    .setX(locations.get(i))
                    .setY(locations.get(i+1)));
        }

        Route route = new Route()
                .setHead(routeDto.isHead())
                .setName(routeDto.getName())
                .setLocationsList(locationList);

        Route save = repository.save(route);

        for (Location location: locationList) {
            location.setRoute(save);
            locationRepository.save(location);
        }
        routeDto.setId(save.getId());

        return routeDto;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public HttpStatus delete(int id) {
        repository.deleteById(id);

        return HttpStatus.NO_CONTENT;
    }

    public RouteDto findRoute(int id) {
        Route route = repository.findByRouteId(id);
        List<Location> locations = locationRepository.findLocationRoteId(route);
        List<Double> list = new ArrayList<>();
        for (Location location: locations) {
            list.add(location.getX());
            list.add(location.getY());
        }

        return new RouteDto()
                .setId(route.getId())
                .setHead(route.isHead())
                .setName(route.getName())
                .setLocations(list);
    }

    public PlanDto findRoutePlan(int id) {
        PlanDto planDto = new PlanDto();

        Route route = repository.findByRouteId(id);
        List<Location> locations = locationRepository.findLocationRoteId(route);
        List<Double> list = new ArrayList<>();
        for (Location location: locations) {
            list.add(location.getX());
            list.add(location.getY());
        }

        int temp = 1;
        for(int i = 3; i < list.size(); i+=2) {
            double distance = Math.sqrt(Math.pow((list.get(i-1)-list.get(i-3)), 2)
                    + Math.pow((list.get(i)-list.get(i-2)), 2));

            distance = Math.round(distance * 10) / 10D;

            planDto.setTotal_distance(planDto.getTotal_distance()+distance);
            planDto.getRoute().add(new RoutePlanDto()
                    .setFrom(temp)
                    .setTo(++temp)
                    .setDistance(distance));
        }

        planDto.setTotal_distance(Math.round(planDto.getTotal_distance() * 10) / 10D);

        return planDto;
    }
}
