package com.epam.usersapi.controller;

import com.epam.usersapi.dao.UsersDAO;
import com.epam.usersapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping("")
    public Collection<User> getUsers() {
        return usersDAO.list();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        User user = usersDAO.get(id);
        if (user == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> add(@RequestBody User user) {
        usersDAO.add(user);
        URI getUri = URI.create(String.format("/user/%d", user.getId()));
        return ResponseEntity.created(getUri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody User user) {
        if (usersDAO.get(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        usersDAO.update(id, user);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (usersDAO.get(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        usersDAO.delete(id);
        return new ResponseEntity<>(OK);
    }
}
