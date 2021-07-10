package me.sabbar.items.service;

import me.sabbar.items.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemService {
    List<Item> findAllItems();

    Item saveNewItem(Item item);

    List<Item> findAllItemsByUserId(Long id);
}
