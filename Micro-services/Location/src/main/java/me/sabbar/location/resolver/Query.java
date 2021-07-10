package me.sabbar.location.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import me.sabbar.location.entity.Location;
import me.sabbar.location.repository.LocationRepository;

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
