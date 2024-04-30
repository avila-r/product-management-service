package com.avila.commerce.security.controller;
import com.avila.commerce.security.model.Login;
import com.avila.commerce.security.model.Registration;
import com.avila.commerce.security.model.User;
import com.avila.commerce.security.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/commerce/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestBody @Valid Login login) {
        return ResponseEntity.ok(
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                login.login(),
                                login.password()
                        )
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid Registration registration){
        return ResponseEntity.ok(userService.createUser(registration));
    }
}