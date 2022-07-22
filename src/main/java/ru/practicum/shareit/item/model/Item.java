package ru.practicum.shareit.item.model;

import lombok.*;

import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Item {

    @EqualsAndHashCode.Exclude
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Boolean available;
    @NotNull
    private User owner;
    private ItemRequest request;
}
