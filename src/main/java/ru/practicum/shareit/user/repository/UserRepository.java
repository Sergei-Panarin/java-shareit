package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAllUsers();
    Optional<User> getUserById(long userId);
    User updateUser(long userId, User user);
    User createUser(User user);
    boolean deleteUser(long userId);
}
