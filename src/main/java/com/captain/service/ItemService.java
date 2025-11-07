package com.captain.service;

import com.captain.model.Item;
import com.captain.model.ItemRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final Map<UUID, Item> itemStorage = new ConcurrentHashMap<>();

    public List<Item> getAllItems(String category) {
        List<Item> allItems = new ArrayList<>(itemStorage.values());
        
        if (category != null && !category.isEmpty()) {
            return allItems.stream()
                    .filter(item -> category.equalsIgnoreCase(item.getCategory()))
                    .collect(Collectors.toList());
        }
        
        return allItems;
    }

    public Item createItem(ItemRequest itemRequest) {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        item.setCategory(itemRequest.getCategory());
        item.setPrice(itemRequest.getPrice());
        item.setCreatedAt(OffsetDateTime.now());
        
        itemStorage.put(item.getId(), item);
        return item;
    }
}
