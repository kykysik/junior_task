package javaJunior.data.mapping;

import javaJunior.data.dto.LocationDto;
import javaJunior.data.entity.Location;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface LocationMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "x", target = "x"),
            @Mapping(source = "y", target = "y"),
    })
    LocationDto locationToDto(Location location);

    @InheritInverseConfiguration
    Location dtoToLocation(LocationDto locationDto);

}
