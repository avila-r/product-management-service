package com.avila.commerce.security.controller;
import com.avila.commerce.security.model.User;
import com.avila.commerce.security.role.UserRole;
import com.avila.commerce.security.service.UserService;
import com.avila.commerce.security.token.TokenService;
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
    private final TokenService tokenService;

    public record Login(String login, String password) { }
    public record LoginResponseMessage(String token, Authentication authentication) { }
    public record Registration(UserRole role, String login, String password) { }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseMessage> login(@RequestBody @Valid Login login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.login(), login.password())
        );
        return ResponseEntity.ok(
                new LoginResponseMessage(
                        tokenService.generate(
                            (User) authentication.getPrincipal()), authentication
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid Registration registration){
        return ResponseEntity.ok(userService.createUser(registration));
    }
}