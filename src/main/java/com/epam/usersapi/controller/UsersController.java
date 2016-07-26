package com.epam.usersapi.controller;

import com.epam.usersapi.dao.IUserDAO;
import com.epam.usersapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping("/list")
    public Collection<User> getUsers() {
        return userDAO.list();
    }
}
