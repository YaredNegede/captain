package com.captain.controller;

import com.captain.api.ItemsApi;
import com.captain.model.Item;
import com.captain.model.ItemRequest;
import com.captain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController implements ItemsApi {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ResponseEntity<List<Item>> getAllItems(String category) {
        List<Item> items = itemService.getAllItems(category);
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<Item> createItem(ItemRequest itemRequest) {
        Item item = itemService.createItem(itemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
}
