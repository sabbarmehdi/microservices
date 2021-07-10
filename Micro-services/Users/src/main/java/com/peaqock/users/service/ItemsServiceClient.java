package com.peaqock.users.service;


import com.peaqock.users.Model.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "item-service")
public interface ItemsServiceClient {

    @GetMapping(value = "/item/user-items")
    public List<Item> getItems(@RequestParam Long userId);
}
