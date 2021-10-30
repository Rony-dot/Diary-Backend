package com.rony.notepadbackend.repository;

import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(String id);

    Optional<Role> findByName(String name);
}
