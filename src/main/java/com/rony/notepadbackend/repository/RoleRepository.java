package com.rony.notepadbackend.repository;

import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(String id);

    Optional<Role> findByRole(String role);
}
