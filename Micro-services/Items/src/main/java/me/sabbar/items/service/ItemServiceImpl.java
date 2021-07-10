package me.sabbar.items.service;

import me.sabbar.items.model.Item;
import me.sabbar.items.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Override
    public List<Item> findAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public Item saveNewItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findAllItemsByUserId(Long id) {

        return itemRepository.findAllByUserId(id);
    }
}
