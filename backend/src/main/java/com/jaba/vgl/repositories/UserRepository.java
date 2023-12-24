package com.jaba.vgl.repositories;


import com.jaba.vgl.models.Role;
import com.jaba.vgl.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    Optional<User> findByRefreshToken(String token);
}
