package ru.practicum.shareit.item.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.requests.service.ItemRequestService;
import ru.practicum.shareit.user.service.UserService;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final UserService userService;
    private final ItemRequestService itemRequestService;

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getOwner()!= null ? item.getOwner().getId() : null,
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }

    public Item toItem(ItemDto itemDto) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                itemDto.getOwnerId() != null ? userService.getUserById(itemDto.getOwnerId()) : null,
                itemRequestService.getItemRequestById(  itemDto.getRequestId())
        );
    }
}
