package com.rony.notepadbackend.controllers;

import com.rony.notepadbackend.dtos.request.UserInfoDto;
import com.rony.notepadbackend.entities.Role;
import com.rony.notepadbackend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/roles")
public interface RoleController {

    @PostMapping("/add")
    ResponseEntity<Void> add(@Valid @RequestBody Role role, BindingResult error);

    @GetMapping("/{id}")
    ResponseEntity<Role> getById(@PathVariable long id);

    @GetMapping("/all")
    ResponseEntity<List<Role>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@Valid @RequestBody Role role, BindingResult error, @PathVariable long id);
}
