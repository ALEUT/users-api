package com.epam.usersapi;

import com.epam.usersapi.dao.IUserDAO;
import com.epam.usersapi.dao.InMemoryUserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public IUserDAO userDAO() {
        return new InMemoryUserDAO();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
