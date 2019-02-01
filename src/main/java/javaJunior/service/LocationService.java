package javaJunior.service;

import javaJunior.data.dto.LocationDto;
import javaJunior.data.entity.Location;
import javaJunior.data.mapping.LocationMapper;
import javaJunior.data.repository.LocationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    private LocationMapper instance = Mappers.getMapper(LocationMapper.class);

    public LocationDto create(Location location) {
            Location result = repository.save(location);

        return instance.locationToDto(result);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public HttpStatus delete(int id) {
            repository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public HttpStatus update(Location location, int id) {
            repository.updateById(location.getName(), location.getX(), location.getY(), id);
        return HttpStatus.NO_CONTENT;
    }

    public LocationDto getLocationById(int id) {
        return instance.locationToDto(repository.findLocationById(id));
    }
}
