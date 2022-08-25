package ru.practicum.shareit.item.service;
import ru.practicum.shareit.item.model.Item;
import java.util.List;

public interface ItemService {

    Item getItemById(long itemId);

    List<Item> getAllItemsByOwnerId(Long ownerId);

    Item createItem(Item item);

    Item updateItem(Long ownerId, Item item);

    List<Item> searchItemsByText(String text);

    boolean deleteItem(long itemId);
}
