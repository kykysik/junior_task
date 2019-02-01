package javaJunior.controller;

import javaJunior.data.dto.LocationDto;
import javaJunior.data.entity.Location;
import javaJunior.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LocationController {

    @Autowired
    private LocationService service;

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public ResponseEntity<LocationDto> createLocation(@RequestBody Location location) {
        return new ResponseEntity(service.create(location), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<LocationDto> deleteLocation(@PathVariable int id) {
        return new ResponseEntity<>(service.delete(id));
    }

    @RequestMapping(value = "/locations/{id}", method = RequestMethod.PUT)
    public ResponseEntity<LocationDto> updateLocation(@RequestBody Location location, @PathVariable int id) {
        return new ResponseEntity<>(service.update(location, id));
    }

    @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
    public LocationDto getLocationById(@PathVariable int id) {
        return service.getLocationById(id);
    }

}
