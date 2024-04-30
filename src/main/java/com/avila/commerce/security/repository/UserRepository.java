package com.avila.commerce.security.repository;
import com.avila.commerce.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    UserDetails findByLogin(String login);
    boolean existsByLogin(String login);
}