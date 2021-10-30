package com.rony.notepadbackend.services;

import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.exception.ResourceNotFoundException;
import com.rony.notepadbackend.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public void add(Role role){
        roleRepository.save(role);
    }

    public Role getById(long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isPresent()){
            return optionalRole.get();
        }
        log.error("role not exist of id : {}", id);
        throw new ResourceNotFoundException("role not found by this id : "+ id);
    }


}
