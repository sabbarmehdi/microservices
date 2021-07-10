package com.peaqock.items.service;

import com.peaqock.items.dto.ItemDto;
import com.peaqock.items.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemService {
    List<Item> findAllItems();

    Item saveNewItem(Item item);

    List<Item> findAllItemsByUserId(Long id);
}
