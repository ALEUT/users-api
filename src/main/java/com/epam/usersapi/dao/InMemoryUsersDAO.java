package com.epam.usersapi.dao;

import com.epam.usersapi.model.User;

import java.util.*;

public class InMemoryUsersDAO implements UsersDAO {

    private Map<UUID, User> idUserMap = new LinkedHashMap<>();

    @Override
    public Collection<User> list() {
        return idUserMap.values();
    }

    @Override
    public User get(UUID id) {
        return idUserMap.get(id);
    }

    @Override
    public void add(User user) {
        user.setId(UUID.randomUUID());
        idUserMap.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        idUserMap.put(user.getId(), user);
    }

    @Override
    public void delete(UUID id) {
        idUserMap.remove(id);
    }
}
