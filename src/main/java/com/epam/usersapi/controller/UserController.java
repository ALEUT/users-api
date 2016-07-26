package com.epam.usersapi.controller;

import com.epam.usersapi.dao.IUserDAO;
import com.epam.usersapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserDAO userDAO;

    @RequestMapping("/{id}")
    public ResponseEntity<?> get(@RequestParam("id") int id) {
        User user = userDAO.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody User user) {
        userDAO.add(user);
        URI getUri = URI.create(String.format("/%d", user.getId()));
        return ResponseEntity.created(getUri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam int id, @RequestBody User user) {
        if (userDAO.get(id) == null) {
            return ResponseEntity.notFound().build();
        }

        userDAO.update(id, user);
        return ResponseEntity.ok(user);
    }
}
