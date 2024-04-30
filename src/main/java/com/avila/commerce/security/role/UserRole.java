package com.avila.commerce.security.role;
import lombok.Getter;

@Getter
public enum UserRole {
    USER ("user"), ADMIN("admin"); private final String role;

    UserRole(String role){
        this.role = role;
    }
}