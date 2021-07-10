package com.peaqock.items.repository;

import com.peaqock.items.dto.ItemDto;
import com.peaqock.items.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findAllByUserId(Long userId);
}
