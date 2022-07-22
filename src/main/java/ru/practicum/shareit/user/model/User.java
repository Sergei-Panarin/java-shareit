package ru.practicum.shareit.user.model;

import lombok.*;

import javax.validation.constraints.Email;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private Long id;
    private String name;
    @Email
    private String email;
}
