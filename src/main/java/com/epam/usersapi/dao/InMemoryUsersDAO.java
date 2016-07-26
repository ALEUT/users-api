package com.epam.usersapi.dao;

import com.epam.usersapi.model.User;

import java.util.*;

public class InMemoryUsersDAO implements UsersDAO {

    private Map<Integer, User> idUserMap = new LinkedHashMap<>();
    private int idCounter = 0;

    @Override
    public Collection<User> list() {
        return idUserMap.values();
    }

    @Override
    public User get(int id) {
        return idUserMap.get(id);
    }

    @Override
    public void add(User user) {
        int id = idCounter++;
        user.setId(id);
        idUserMap.put(id, user);
    }

    @Override
    public void update(User user) {
        idUserMap.put(user.getId(), user);
    }

    @Override
    public void delete(int id) {
        idUserMap.remove(id);
    }
}
