package ru.practicum.shareit.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class InMemoryItemRepository implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();
    private long counterId = 1;

    public long getNextId() {
        return counterId++;
    }

    @Override
    public Optional<Item> getItemById(long itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public List<Item> getAllItemsByOwnerId(long ownerId) {
        return items.values().stream()
                .filter(item -> item.getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    @Override
    public Item createItem(Item item) {
        item.setId(getNextId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        items.remove(item.getId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> searchItemsByText(String text) {
        if (text.isBlank()) {
            return Collections.emptyList();
        }
        String lowerCaseText = text.toLowerCase();
        Set<Item> searchedItemsByName = items.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().toLowerCase().contains(lowerCaseText))
                .collect(Collectors.toSet());
        Set<Item> searchedItemsByDescription = items.values().stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getDescription().toLowerCase().contains(lowerCaseText))
                .collect(Collectors.toSet());
        searchedItemsByName.addAll(searchedItemsByDescription);
        return List.copyOf(searchedItemsByName);
    }

    @Override
    public boolean deleteItem(long itemId) {
        items.remove(itemId);
        return !items.containsKey(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return List.copyOf(items.values());
    }
}
