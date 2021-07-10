package com.peaqock.location.mutator;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.peaqock.location.entity.Location;
import com.peaqock.location.exception.LocationNotFoundException;
import com.peaqock.location.repository.LocationRepository;

import java.util.Optional;

@DgsComponent
public class Mutation {
    private LocationRepository locationRepository;

    public Mutation(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @DgsData(parentType = "Mutation", field = "newLocation")
    public Location newLocation(String name, String address) {
        Location location = new Location(name, address);
        locationRepository.save(location);
        return location;
    }

    @DgsData(parentType = "Mutation", field = "deleteLocation")
    public boolean deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return true;
    }

    @DgsData(parentType = "Mutation", field = "updateLocationName")
    public Location updateLocationName(String newName, Long id) {
        Optional<Location> optionalLocation =
                locationRepository.findById(id);

        if(optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setName(newName);
            locationRepository.save(location);
            return location;
        } else {
            throw new LocationNotFoundException("Location Not Found", id);
        }
    }
}
