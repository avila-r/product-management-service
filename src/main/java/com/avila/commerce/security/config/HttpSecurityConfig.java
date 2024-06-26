package com.avila.commerce.security.config;
import com.avila.commerce.security.filter.HttpSecurityFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration @EnableWebSecurity
@AllArgsConstructor
public class HttpSecurityConfig {
    private final HttpSecurityFilter httpSecurityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/commerce/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/commerce/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/commerce/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/commerce/product/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/commerce/auth/**").permitAll()
                )
                .addFilterBefore(httpSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}