package javaJunior.controller;

import javaJunior.data.dto.PlanDto;
import javaJunior.data.dto.RouteDto;
import javaJunior.data.entity.Route;
import javaJunior.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RouteController {

    @Autowired
    private RouteService service;

    @RequestMapping(value = "/routes", method = RequestMethod.POST)
    public ResponseEntity<RouteDto> createRoute(@RequestBody RouteDto route) {
        return new ResponseEntity(service.create(route), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Route> deleteRoute(@PathVariable int id) {
        return new ResponseEntity<>(service.delete(id));
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.GET)
    public RouteDto getRouteById(@PathVariable int id) {
        return service.findRoute(id);
    }

    @RequestMapping(value = "/routes/{id}/plan", method = RequestMethod.GET)
    public PlanDto getPlanById(@PathVariable int id) {
        return service.findRoutePlan(id);
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<RouteDto> updateRoute(@Validated @RequestBody RouteDto routeDto, Errors errors, @PathVariable int id) {
        if (errors.hasFieldErrors()) {
            return new ResponseEntity<RouteDto>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<RouteDto>(HttpStatus.CREATED);
    }
}
