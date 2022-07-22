package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemById(long itemId) {
        return itemRepository.getItemById(itemId)
                .orElseThrow(() -> new NotFoundException("Введено некорректное значение id"));
    }

    @Override
    public List<Item> getAllItemsByOwnerId(Long ownerId) {

        return itemRepository.getAllItemsByOwnerId(ownerId);
    }

    @Override
    public Item createItem(Item item) {
        if (itemRepository.getAllItems().contains(item)) {
            throw new NotFoundException("Такой item уже зарегистрирован");
        }
        return itemRepository.createItem(item);
    }

    @Override
    public Item updateItem(Long ownerId, Item item) {
        Item updateItem = getItemById(item.getId());
        if (!updateItem.getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("Редактировать вещь может только её владелец");
        }
        if (item.getName() != null && !item.getName().isBlank()) {
            updateItem.setName(item.getName());
        }
        if (item.getDescription() != null && !item.getDescription().isBlank()) {
            updateItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            updateItem.setAvailable(item.getAvailable());
        }
        return itemRepository.updateItem(updateItem);
    }

    @Override
    public List<Item> searchItemsByText(String text) {
        return itemRepository.searchItemsByText(text);
    }

    @Override
    public boolean deleteItem(long itemId) {
        return itemRepository.deleteItem(itemId);
    }
}
