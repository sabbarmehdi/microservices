package com.peaqock.location.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.peaqock.location.entity.Location;
import com.peaqock.location.repository.LocationRepository;

@DgsComponent
public class Query  {
    private LocationRepository locationRepository;

    public Query(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @DgsQuery
    public Iterable<Location> findAllLocations() {
        return locationRepository.findAll();
    }
}
