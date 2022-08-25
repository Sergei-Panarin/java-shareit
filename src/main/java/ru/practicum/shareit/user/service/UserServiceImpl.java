package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ConflictException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(long userId) {
        return userRepository.getUserById(userId)
                .orElseThrow(() -> new NotFoundException("Введено некорректное значение id"));
    }

    @Override
    public User updateUser(long userId, User user) {
        getUserById(userId);
        validateUserEmail(user);
        return userRepository.updateUser(userId, user);
    }

    @Override
    public User createUser(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException("Введен некорректный email");
        }
        validateUserEmail(user);
        return userRepository.createUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        getUserById(userId);
        return userRepository.deleteUser(userId);
    }

    private User validateUserEmail(User user) {
        for (User u : getAllUsers()) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new ConflictException("Такой email уже зарегистрирован");
            }
        }
        return user;
    }
}
