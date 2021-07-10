package me.sabbar.location.repository;

import me.sabbar.location.entity.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
