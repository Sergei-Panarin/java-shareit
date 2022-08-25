package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Optional<Item> getItemById(long itemId);

    List<Item> getAllItemsByOwnerId(long ownerId);

    Item createItem(Item item);

    Item updateItem(Item item);

    List<Item> searchItemsByText(String text);

    boolean deleteItem(long itemId);

    List<Item> getAllItems();
}
