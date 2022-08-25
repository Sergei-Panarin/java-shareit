package ru.practicum.shareit.requests.model;

import lombok.Value;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Value
public class ItemRequest {

    Long id;
    String description;
    User requestor;
    LocalDateTime created;
}
