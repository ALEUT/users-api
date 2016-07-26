package com.epam.usersapi.controller;

import com.epam.usersapi.dao.IUserDAO;
import com.epam.usersapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping("/list")
    public Collection<User> getUsers() {
        return userDAO.list();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        User user = userDAO.get(id);
        if (user == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody User user) {
        userDAO.add(user);
        URI getUri = URI.create(String.format("/user/%d", user.getId()));
        return ResponseEntity.created(getUri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody User user) {
        if (userDAO.get(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        userDAO.update(id, user);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (userDAO.get(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        userDAO.delete(id);
        return new ResponseEntity<>(OK);
    }
}
