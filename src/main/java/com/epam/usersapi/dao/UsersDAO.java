package com.epam.usersapi.dao;

import com.epam.usersapi.model.User;

import java.util.Collection;
import java.util.UUID;

public interface UsersDAO {

    Collection<User> list();

    User get(UUID id);

    void add(User user);

    void update(User user);

    void delete(UUID id);
}
