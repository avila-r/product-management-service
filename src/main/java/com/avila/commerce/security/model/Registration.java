package com.avila.commerce.security.model;
import com.avila.commerce.security.role.UserRole;

public record Registration(UserRole role, String login, String password) { }