package com.rony.notepadbackend.repository;

import com.rony.notepadbackend.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @EntityGraph(attributePaths = "roles")
        // Handles lazy loading
    Optional<User> findByUsername(String username);

//    Optional<User> findByJwtToken(String jwtToken);

    List<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);
}
