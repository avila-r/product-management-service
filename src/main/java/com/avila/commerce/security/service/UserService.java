package com.avila.commerce.security.service;
import com.avila.commerce.exception.user.UsernameAlreadyExistsException;
import com.avila.commerce.security.controller.AuthenticationController;
import com.avila.commerce.security.model.User;
import com.avila.commerce.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(@NotNull AuthenticationController.Registration registration){
        if (userRepository.existsByLogin(registration.login())) throw new UsernameAlreadyExistsException("Username already exists");
        else return userRepository.save(new User(
                registration.role(),
                registration.login(),
                new BCryptPasswordEncoder().encode(registration.password())
        ));
    }
}