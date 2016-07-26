package com.epam.usersapi.dao;

import com.epam.usersapi.model.User;

import java.util.Collection;

public interface UsersDAO {

    Collection<User> list();

    User get(int id);

    void add(User user);

    void update(User user);

    void delete(int id);
}
