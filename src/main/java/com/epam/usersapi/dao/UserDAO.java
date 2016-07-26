package com.epam.usersapi.dao;

import com.epam.usersapi.model.User;

import java.util.Collection;

public interface UserDAO {

    Collection<User> list();

    User get(int id);

    void add(User user);

    void update(int id, User user);

    void delete(int id);
}
