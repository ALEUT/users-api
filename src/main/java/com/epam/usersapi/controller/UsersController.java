package com.epam.usersapi.controller;

import com.epam.usersapi.dao.UsersDAO;
import com.epam.usersapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

import static com.epam.usersapi.controller.UsersController.REQUEST_MAPPING;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(REQUEST_MAPPING)
public class UsersController {

    protected static final String REQUEST_MAPPING = "users";

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping(method = GET)
    public Collection<User> list() {
        return usersDAO.list();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<User> get(@PathVariable UUID id) {
        User user = usersDAO.get(id);
        if (user == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<Void> add(@RequestBody User user) {
        usersDAO.add(user);
        URI getUri = URI.create(String.format("/%s/%s", REQUEST_MAPPING, user.getId()));
        return ResponseEntity.created(getUri).build();
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<Void> update(@RequestBody User user) {
        if (usersDAO.get(user.getId()) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        usersDAO.update(user);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (usersDAO.get(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        usersDAO.delete(id);
        return new ResponseEntity<>(OK);
    }
}
