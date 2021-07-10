package me.sabbar.items.controller;

import me.sabbar.items.model.Item;
import me.sabbar.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/item")
@RepositoryRestController
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Item>> findItems(){
        return new ResponseEntity<>(itemService.findAllItems(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> addNewItem(@RequestBody Item item){
        return new ResponseEntity<Item>(itemService.saveNewItem(item), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user-items")
    public ResponseEntity<List<Item>> getItemByUserId(@RequestParam(value = "userId") Long userId){
        return new ResponseEntity<List<Item>>(itemService.findAllItemsByUserId(userId), HttpStatus.OK);
    }


}

