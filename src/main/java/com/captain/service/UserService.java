package com.captain.service;

import com.captain.model.User;
import com.captain.model.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<UUID, User> userStorage = new ConcurrentHashMap<>();

    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCreatedAt(OffsetDateTime.now());
        
        userStorage.put(user.getId(), user);
        return user;
    }

    public User getUserById(UUID userId) {
        User user = userStorage.get(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId);
        }
        return user;
    }

    public User updateUser(UUID userId, UserRequest userRequest) {
        User existingUser = getUserById(userId);
        
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        
        userStorage.put(userId, existingUser);
        return existingUser;
    }

    public void deleteUser(UUID userId) {
        if (!userStorage.containsKey(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId);
        }
        userStorage.remove(userId);
    }
}
