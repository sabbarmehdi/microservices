package com.peaqock.location.repository;

import com.peaqock.location.entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
