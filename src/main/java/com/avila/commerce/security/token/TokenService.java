package com.avila.commerce.security.token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.avila.commerce.exception.user.InvalidTokenGenerationException;
import com.avila.commerce.exception.user.InvalidTokenSubmitException;
import com.avila.commerce.security.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generate(@NotNull User user) {
        try {
            return JWT.create()
                    .withIssuer("commerce-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(getExpirationDate())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e){
            throw new InvalidTokenGenerationException("Error creating token");
        }
    }

    public String validate(String token){
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("commerce-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new InvalidTokenSubmitException();
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}