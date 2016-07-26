package com.epam.usersapi;

import com.epam.usersapi.dao.UsersDAO;
import com.epam.usersapi.dao.InMemoryUsersDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public UsersDAO usersDAO() {
        return new InMemoryUsersDAO();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
